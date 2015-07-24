(ns ^:figwheel-always gg.zfo.www.core
  (:require [reagent.core :as reagent
             :refer [atom]]
            [re-frame.core :as re-frame]
            [devtools.core :as devtools]
            [gg.zfo.www.handlers]
            [gg.zfo.www.subs]
            [gg.zfo.www.nav.routes :as routes]
            [gg.zfo.www.index.core :as index]))


(defonce root-node
  (.getElementById js/document "gg.zfo.www.core"))

(defn mount-root []
  (reagent/render [index/root-component] root-node))

(defn unmount-root []
  (reagent/unmount-component-at-node root-node))


(defn reset []
  (if-not (unmount-root)
    (do
      (routes/make-routes)
      (re-frame/dispatch-sync [:initialize-db])))
  (mount-root))


(defonce reload-counter
  (atom 1))

(defn onreload []
  (.info js/console (str "reload " @reload-counter))
  (swap! reload-counter inc))


(defonce init
  (do
    (devtools/install!)
    (reset)))

