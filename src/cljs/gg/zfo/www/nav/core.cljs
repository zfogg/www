(ns gg.zfo.www.nav.core
  (:require [re-com.validate
             :refer [string-or-atom?]
             :refer-macros [validate-args-macro]]
            [re-com.util
             :refer [deref-or-value]]
            [gg.zfo.www.nav.history
             :refer [nav!]]))


(defn anchor_on-click [href event]
  (.preventDefault event)
  (nav! href))

(def anchor-args-desc
  [{:name :href    :required true  :type "string | atom"  :validate-fn string-or-atom?  :description "text label for the button"}
   {:name :text    :required true  :type "string | atom"  :validate-fn string-or-atom?  :description "target URL"}
   {:name :classes :required false :type "string | atom"  :validate-fn string-or-atom?  :description "CSS class names, space separated"}])

(defn anchor []
  (fn
    [& {:keys [href text classes] :as args}]
    {:pre [(validate-args-macro anchor-args-desc args "anchor")]}
    [:a {:href                       (deref-or-value href)
         :on-click #(anchor_on-click (deref-or-value href) %)
         :class                      (deref-or-value classes)}
     (deref-or-value text)]))

