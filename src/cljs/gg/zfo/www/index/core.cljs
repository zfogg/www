(ns gg.zfo.www.index.core
  (:require [re-com.core :as re-com]
            [re-frame.core :as re-frame]
            [gg.zfo.www.index.home  :refer [home-panel]]
            [gg.zfo.www.index.about :refer [about-panel]]))


(defmulti panels identity)

(defmethod panels :home-panel  [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default     [] [:div])


(defn root-component []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [(panels @active-panel)]])))

