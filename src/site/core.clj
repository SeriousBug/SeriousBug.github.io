(ns site.core
  (:require [hiccup.page :as hp]))

(defn page [data]
  (hp/html5
   [:div {:style "max-width: 600px;"}
    (-> data :entry :content)]))
