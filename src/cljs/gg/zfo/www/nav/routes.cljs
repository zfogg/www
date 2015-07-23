(ns gg.zfo.www.nav.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:require [re-frame.core :as re-frame]
            [secretary.core :as secretary]
            [gg.zfo.www.nav.history
             :refer [has-history? get-path-token hook-browser-navigation!]]))


(defn make-routes []
  (if-not has-history?
    (do
      (secretary/set-config! :prefix "#")
      (-> js/document .-location (set! (str "#" (get-path-token))))))


  (defroute "/" []
    (re-frame/dispatch [:set-active-panel :home-panel]))

  (defroute "/about" []
    (re-frame/dispatch [:set-active-panel :about-panel]))


  (hook-browser-navigation!))

