(ns site.styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule]]))

(def textcolor-main "rgba(0, 0, 0, 0.87)")

(defstyles base
  [:.main
   {:max-width "600px"}]
  [:h1 {:font-size "1.5em"}]
  ["h1:not(:first-child)" {:margin-top "2em"}]
  [:h2 {:font-size "1.3em"}]
  [:h3 {:font-size "1.15em"}]
  ["img, .img" {:border-radius "5px" :max-width "100%"}]
  [:.sidebar
   {:max-width "300px"}
   [">a,span" {:display "block" :padding "10px 0"}]
   [:.gpg {:font-size "0.8em" :margin-left "20px"}]]
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
    :font-family "Ubuntu, sans-serif"
    :font-size   "16px"
    :line-height 1.2}]
  [:a {:text-decoration "none" :color "#ae3100"}]
  ["a:link"]
  ["a:visited" {:color "#681e00"}]
  ["a:hover"]
  ["a:active"]

  [:.other-stuff
   {:margin-top "4em"}
   [:img {:float "left" :max-width "250px" :margin-right "40px"}]]


  [:.name {:padding "10px 0" :font-size "1.2em"}]
  [:.publication [:p [:a {:padding "0 10px"}]]]
  [:.publication [:div {:padding ".2em 10px .2em 30px"}]]
  ;; Create a separator between publications with the border
  [".publication:not(:last-child)" {:border-bottom "1px solid black"}]
  ;;
  [:.conf {:font-size "14px" :color "rgba(0, 0, 0, 0.70)"}])
