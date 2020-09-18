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
  (comp (perun/pandoc)
        (perun/highlight)
        (sift :to-resource #{#"^img/(.*)"})
        (sift :move {#"^img/(.*)" "public/img/$1"})
        (sift :to-resource #{#"^extra/(.*)"})
        (sift :move {#"^extra/(.*)" "public/extra/$1"})
        (garden :styles-var 'site.styles/base :output-to "public/main.css")
        (perun/render :renderer 'site.core/page)
        (perun/sitemap :filename "sitemap.xml")
        (sift :move {#"^public/(.*)" "$1"})))

(deftask dev []
  (comp (watch)
        (build)
        (serve :port 8000 :resource-root "public")))

(deftask publish []
  (comp (build)
        (target :dir #{"docs"})))
