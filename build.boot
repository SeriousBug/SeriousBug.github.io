(set-env!
 :source-paths #{"src"}
 :resource-paths #{"content"}
 :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                 [deraen/boot-livereload "0.2.1" :scope "test"]
                 [hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                 [garden "1.3.10"]
                 [pandeiro/boot-http "0.8.3" :exclusions [org.clojure/clojure]]])

(require '[io.perun :as perun]
         '[deraen.boot-livereload :refer [livereload]]
         '[pandeiro.boot-http :refer [serve]]
         '[site.core])

(deftask build-website []
  (comp (perun/markdown)
        (perun/render :renderer 'site.core/page)
        (perun/sitemap :filename "sitemap.xml")))

(deftask serve-website []
  (comp (livereload)
        (build-website)
        (serve :port 8000 :resource-root "public")
;        (repl)
        (wait)))

(deftask compile-website []
  (comp (build-website)
        (target)))
