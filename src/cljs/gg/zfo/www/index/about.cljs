(ns gg.zfo.www.index.about
  (:require [re-com.core :as re-com]
            [gg.zfo.www.nav.core :as nav]))


(defn about-panel []
  [re-com/v-box
   :class "panel"
   :gap "1em"
   :children [[re-com/title
               :label "This is the About Page."
               :level :level1]
              [nav/anchor
               :href "/"
               :text "go to Home Page"]]])

