(ns gg.zfo.www.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]
              [re-com.validate
               :refer [string-or-atom?]
               :refer-macros [validate-args-macro]]
              [re-com.util
               :refer [deref-or-value]]
              [gg.zfo.www.routes :as routes]))


(def nav-anchor-args-desc
  [{:name :href     :required true   :type "string | atom"  :validate-fn string-or-atom?  :description "text label for the button"}
   {:name :text     :required true   :type "string | atom"  :validate-fn string-or-atom?  :description "target URL"}
   {:name :classes  :required false  :type "string"         :validate-fn string?          :description "CSS class names, space separated"}])

(defn nav-anchor []
  (fn
    [& {:keys [href text classes] :as args}]
    {:pre [(validate-args-macro nav-anchor-args-desc args "nav-anchor")]}
    (let [href (deref-or-value href)
          text (deref-or-value text)]
      [:a {:href href
           :on-click #(do (.preventDefault %)
                          (routes/nav! href))
           :class classes}
       text])))


;; --------------------
(defn home-title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/title
       :label (str "Hello from " @name ". This is the Home Page.")
       :level :level1])))

(defn link-to-about-page []
  [nav-anchor
   :href "/about"
   :text "go to About Page"])

(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[home-title] [link-to-about-page]]])

;; --------------------
(defn about-title []
  [re-com/title
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [nav-anchor
   :href "/"
   :text "go to Home Page"])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[about-title] [link-to-home-page]]])

;; --------------------
(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [(panels @active-panel)]])))
