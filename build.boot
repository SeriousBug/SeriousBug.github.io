(set-env!
 :source-paths #{"src"}
 :resource-paths #{"content"}
 :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                 [hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                 [garden "1.3.10"]
                 [org.martinklepsch/boot-garden "1.3.2-0"]
                 [pandeiro/boot-http "0.8.3" :exclusions [org.clojure/clojure]]])

(require '[io.perun :as perun]
         '[pandeiro.boot-http :refer [serve]]
         '[org.martinklepsch.boot-garden :refer [garden]]
         '[site.core])

(deftask build []
  (comp (perun/draft)
        (perun/pandoc :cmd-opts ["-f" "markdown" "-t" "html5"])
        (sift :to-resource #{#"^img/(.*)"})
        (sift :to-resource #{#"^extra/(.*)"})
        (sift :to-resource #{#"^CNAME"})
        (garden :styles-var 'site.styles/base :output-to "main.css")
        (perun/ttr)  ;; Time to read
        (perun/word-count)
        (perun/render :renderer 'site.core/page)
        (perun/permalink :filterer (fn [p] (not= (:slug p) "index")))
        (perun/collection :renderer 'site.core/page-blog
                          :filterer (fn [p] (:title p)) ; don't list anything without a title
                          :sortby :date
                          :page "blog/index.html" :out-dir "")
        (perun/sitemap :filename "sitemap.xml")
        (sift :move {#"^public/(.*)" "$1"}))) ; perun/render ignores out-dir for some reason
        


(deftask dev []
  (comp (watch)
        (build)
        (serve :port 8000 :resource-root "")))

(deftask publish []
  (comp (build)
        (target :dir #{"docs"})))
