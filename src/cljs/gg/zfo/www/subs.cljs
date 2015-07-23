(ns gg.zfo.www.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))


(defonce sub--name
  (re-frame/register-sub
    :name
    (fn [db]
      (reaction (:name @db)))))

(defonce sub--active-panel
  (re-frame/register-sub
    :active-panel
    (fn [db _]
      (reaction (:active-panel @db)))))

