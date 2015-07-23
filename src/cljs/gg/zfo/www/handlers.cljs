(ns gg.zfo.www.handlers
    (:require [re-frame.core :as re-frame]
              [gg.zfo.www.db :as db]))


(defonce handler--initialize-db
  (re-frame/register-handler
    :initialize-db
    (fn  [_ _]
      db/default-db)))

(defonce handler--set-active-panel
  (re-frame/register-handler
    :set-active-panel
    (fn [db [_ active-panel]]
      (assoc db :active-panel active-panel))))

