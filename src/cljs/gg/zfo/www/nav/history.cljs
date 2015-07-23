(ns gg.zfo.www.nav.history
  (:import goog.History
           goog.history.Html5History)
  (:require [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [clojure.string :as string]))


(defonce loc
  js/window.location)


(defonce has-history?
  (some #(= "history" %)
    (prim-seq (.-classList js/document.documentElement))))

(defonce history
  (if has-history?
    (doto (Html5History.)
      (.setUseFragment false)
      (.setPathPrefix (str loc.protocol "//" loc.host)))
    (doto (History.))))


(defn get-path-token []
  (if has-history?
    (str loc.pathname loc.search)
    (string/replace-first
      (str loc.hash loc.search)
      #"\#(.*)" "$1")))


(defn nav! [token]
  (if (= token (get-path-token))
    (.replaceToken history token)
    (.setToken     history token)))


(defn hook-browser-navigation! []
  (doto history
    (events/listen
      EventType/NAVIGATE
      #(secretary/dispatch! (get-path-token)))
    (.setEnabled true)))

