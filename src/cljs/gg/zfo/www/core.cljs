(ns ^:figwheel-always gg.zfo.www.core
    (:require [reagent.core :as reagent
               :refer [atom]]
              [re-frame.core :as re-frame]
              [gg.zfo.www.handlers]
              [gg.zfo.www.subs]
              [gg.zfo.www.routes :as routes]
              [gg.zfo.www.views :as views]))


(defn get-root-node []
  (.getElementById js/document "gg.zfo.www.core"))

(defn mount-root []
  (reagent/render [views/main-panel] (get-root-node)))

(defn unmount-root []
  (reagent/unmount-component-at-node (get-root-node)))


(defn reset []
  (if-not (unmount-root)
    (do
      (routes/app-routes)
      (re-frame/dispatch-sync [:initialize-db])))
  (mount-root))


(defonce reload-counter
  (atom 1))

(defn onreload []
  (.info js/console (str "reload " @reload-counter))
  (swap! reload-counter inc))


(defonce init
  (reset))

