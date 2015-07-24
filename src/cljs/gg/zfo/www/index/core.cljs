(ns gg.zfo.www.index.core
  (:require [re-com.core :as re-com]
            [re-frame.core :as re-frame]
            [gg.zfo.www.index.home  :refer [home-panel]]
            [gg.zfo.www.index.about :refer [about-panel]]))


(defmulti panels identity)

(defmethod panels :home-panel  [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default     [] [:div])


(defn header-component []
  [re-com/title
   :label (str "Header!")
   :level :level2])


(defn footer-component []
  [re-com/box
   :class "footer"
   :child [re-com/h-box
           :class "blobs"
           :justify :center
           :children (map (fn [i]
                            (let [i (inc i)]
                              [re-com/box :class "blob-wrapper"
                               :child [:div {:class "blob"} i]]))
                          (range 16))]])


(defn root-component []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :justify :around
       :align :center
       :children [[re-com/box
                   :class "header-wrapper"
                   :child [header-component]]
                  [re-com/box
                   :size "1"
                   :child [panels @active-panel]]
                  [re-com/box
                   :class "footer-wrapper"
                   :width "100%"
                   :child [footer-component]]]])))

