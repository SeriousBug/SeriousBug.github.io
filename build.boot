(set-env!
 :source-paths #{"src"}
 :resource-paths #{"content"}
 :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                 [hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                 [pandeiro/boot-http "0.8.3" :exclusions [org.clojure/clojure]]])

(require '[io.perun :as perun]
         '[pandeiro.boot-http :refer [serve]]
         '[site.core])

(deftask build-website []
  (comp (perun/markdown)
        (perun/render :renderer 'site.core/page)
        (perun/sitemap :filename "sitemap.xml")))

(deftask serve-website []
  (comp (build-website)
        (serve :port 8000 :resource-root "public")
        (wait)))

(deftask compile-website []
  (comp (build-website)
        (target)))
