(ns site.core
  (:require [hiccup.page :as hp]))


(defn format-time [time] (.format (-> time .toInstant (.atZone (java.time.ZoneId/systemDefault)) .toLocalDate) (java.time.format.DateTimeFormatter/ofPattern "MMM d, yyyy")))


(defn page-header [& [title]]
  [:head
   [:meta {:charset "utf-8"}]
   [:link {:rel "stylesheet" :href "/main.css"}]
   [:link {:rel "stylesheet" :href "/extra/emacs.css"}]
   [:title (when title (str title " - ")) "Homepage of Kaan Genç"]
   [:link {:href
           "https://fonts.googleapis.com/css2?family=Ubuntu+Mono&family=Ubuntu:ital,wght@0,400;0,700;1,400;1,700&display=swap"
           :rel "stylesheet"}]])

(defn page-left-column [& [slug]]
  [:div.sidebar.column
   (when (not= slug "index") [:a.home {:href "/"} "Home"])
   [:img.picture {:alt "A photo of Kaan Genç, after his OOPSLA 2019 talk." :src "/img/profile.jpg"}]
   [:div.name "Kaan Genç"]
   [:div.title "PhD Student"]
   [:div.department "Computer Science & Engineering"]
   [:div.affiliation "The Ohio State University"]
   [:span [:a.email {:href "mailto:genc.5@osu.edu"} "genc.5@osu.edu"] [:a.gpg {:href "/extra/kaangenc.gpg"} "GPG key"]]
   [:a.github {:href "https://github.com/SeriousBug"} "Github"]
   [:a.linkedin {:href "https://www.linkedin.com/in/kaan-genc-8489b9205/"} "LinkedIn"]
   [:a.cv {:href "/extra/cv.pdf"} "CV"]
   [:a.blog {:href "/blog/"} "Blog"]
   [:a.twitter {:href "https://twitter.com/KaanGencCS"} "Twitter"]
   [:a.researchr {:href "https://conf.researchr.org/profile/kaangenc"} "Researchr"]])




(defn page [{global-meta :meta posts :entries post :entry}]
  (hp/html5
   (page-header (:title post))
   (page-left-column (:slug post))
   (if (= "index" (:slug post))
     ;; Index page
     [:div.main.column (:content post)]
     ;; Blog post
     [:div.main.column
      [:h1.post-title (:title post)]
      [:div.date "Written at " (-> post :date format-time)]
      (when (:modified post) [:div.modified "Last edited at " (-> post :modified format-time)])
      [:div.ttr (:word-count post) " words, takes about " (:ttr post) " minutes to read"]
      (:content post)])))


(defn page-blog [{global-meta :meta posts :entries post :entry}]
  (hp/html5
   (page-header "Blog")
   (page-left-column)
   (into [:div.main.column [:h1 "My thoughts on software & computer science"]]
         (map (fn [{:keys [title permalink ttr date] :as post}]
                [:a.post-listing {:href permalink} [:span.title title] [:br] [:span.ttr ttr " minute read, "] [:span.date (format-time date)]])
            posts))))
