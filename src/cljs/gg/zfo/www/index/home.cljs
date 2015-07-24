(ns gg.zfo.www.index.home
  (:require [re-com.core :as re-com]
            [re-frame.core :as re-frame]
            [reagent.core :as reagent]
            [gg.zfo.www.nav.core :as nav]))


(defn home-panel []
  (let [my-name (re-frame/subscribe [:my-name])]
    [re-com/v-box
     :class "panel"
     :gap "1em"
     :children [[re-com/title
                 :label (str "Hello from " @my-name ". This is the Home Page.")
                 :level :level1]
                [nav/anchor
                 :href "/about"
                 :text "go to About Page"]]]
    ))

