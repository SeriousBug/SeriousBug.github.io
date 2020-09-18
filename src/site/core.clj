(ns site.core
  (:require [hiccup.page :as hp]
            [garden.core :as gc]))


(defn page [data]
  (hp/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:link {:rel "stylesheet" :href "main.css"}]]
    [:title "Homepage of Kaan Genç"]
   [:link {:href
           "https://fonts.googleapis.com/css2?family=Ubuntu:ital,wght@0,400;0,700;1,400;1,700&display=swap"
           :rel "stylesheet"}]
   (when (= "index" (-> data :entry :slug))
     [:div.sidebar.column
      [:img.picture {:alt "A photo of Kaan Genç, after his OOPSLA 2019 talk." :src "/img/profile.jpg"}]
      [:div.name "Kaan Genç"]
      [:div.title "PhD Student"]
      [:div.department "Computer Science & Engineering"]
      [:div.affiliation "The Ohio State University"]
      [:span [:a.email {:href "mailto:genc.5@osu.edu"} "genc.5@osu.edu"] [:a.gpg {:href "/extra/kaangenc.gpg"} "GPG key"]]
      [:a.github {:href "https://github.com/SeriousBug"} "Github"]
      [:a.researchr {:href "https://conf.researchr.org/profile/kaangenc"} "Researchr"]
      [:a.cv {:href "/extra/cv.pdf"} "CV"]])
   [:div.main.column
    (-> data :entry :content)]))
