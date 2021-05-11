(ns site.styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule]]))

(def textcolor-main "rgba(0, 0, 0, 0.87)")

(defstyles base
  ; normalize.css v8.0.1 | MIT License | github.com/necolas/normalize.css
  ; Adapted to Garden and trimmed
  [:html {"-webkit-text-size-adjust" "100%"}]
  [:body {:margin "0"}]
  [:main {:display "block"}]
  [:a {:background-color "transparent"}]
  [:img {:border-style "none"}]
  ; Website styles
  [:.main
   {:max-width "600px"
    :line-height "1.4"}]
  [:h1 {:font-size "1.5em"}]
  ["h1:not(:first-child)" {:margin-top "2em"}]
  [:h2 {:font-size "1.3em"}]
  [:h3 {:font-size "1.15em"}]
  ["img, .img" {:border-radius "5px" :max-width "100%"}]
  [:.sidebar
   {:max-width "300px"}
   [:.gpg {:font-size "0.8em" :margin-left "20px"}]]
  [".sidebar>a,.sidebar>span" {:display "block" :padding ".5em 0"}]
  [:.post-listing {:margin ".5em 0" :display "block"} [:.ttr {:margin-left "20px"}]]
  [:.column
   {:flex "auto"
    :padding "20px"}]
  [:.title {:margin-bottom "0.5em"}]
  [:body
   {:display "flex"
    :flex-direction "row"
    :flex-wrap "wrap"
    :justify-content "center"
    :color textcolor-main
    :background-color "#ffead1"
    :font-family "'Ubuntu', sans-serif"
    :font-size   "16px"
    :line-height 1.2}]
  [:a {:text-decoration "none" :color "#ae3100"}]
  ["a:link"]
  ["a:visited" {:color "#932900"}]
  ["a:hover"]
  ["a:active"]

  [".date, .modified, .ttr" {:color "rgba(0, 0, 0, 0.67)" :text-align "right"}]

  [:.other-stuff
   {:margin-top "4em"}
   [:img {:float "left" :max-width "250px" :margin-right "40px"}]]

  ["code, pre" {:font-size "0.95em" :font-family "'Ubuntu Mono', monospace"}]
  ["pre, :not(pre) > code" {:background-color "rgba(0, 0, 0, 0.15)" :overflow-x "auto"
                            :border-radius "10px"
                            :padding "3px"
                            :margin "-3px 0"}]

  [:a.home {:font-size "1.2em" :margin "0 20px"}]

  [:.name {:padding "10px 0" :font-size "1.2em"}]
  [:.publication [:p [:a {:padding "0 10px"}]]]
  [:.publication [:div {:padding ".2em 10px .2em 30px"}]]
  ;; Create a separator between publications with the border
  [".publication:not(:last-child)" {:border-bottom "1px solid black"}]
  ;;
  [:.conf {:font-size "14px" :color "rgba(0, 0, 0, 0.70)"}]
  [:.spacer {:height "2rem"}])
