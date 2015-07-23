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
   :align-self :center
   :justify    :end
   :child [:div {:class "blobs"}
           (map (fn [i]
             (let [i (inc i)]
               [:div {:class "blob" :key i} i]))
              (range 4))]])


(defn root-component []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :attr {:id "root-component"}
       :height "100%"
       :justify :around
       :align :center
       :children [[header-component]
                  [re-com/box
                   :size "1"
                   :child [panels @active-panel]]
                  [footer-component]]])))

