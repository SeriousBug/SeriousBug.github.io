(ns site.core
  (:require [hiccup.page :as hp]
            [garden.core :as gc]))

(def textcolor-main "rgba(0, 0, 0, 0.87)")

(def css
  (gc/css 
   [:body 
    {:max-width "600px"
     :color textcolor-main}]
   ))

(defn page [data]
  (hp/html5
   [:head [:style css]]
   [:div
    (-> data :entry :content)]))
