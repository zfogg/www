(defproject gg.zfo.www "0.1.0-SNAPSHOT"
  :source-paths ["src/clj"]


  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3211"]
                 [reagent "0.5.0"]
                 [re-frame "0.4.1"]
                 [re-com "0.5.4"]
                 [secretary "1.2.3"]]

  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.3" :exclusions [cider/cider-nrepl]]]

  :hooks [leiningen.cljsbuild]


  :figwheel {
    :http-server-root  "public"
    :server-port       3449
    :server-ip         "0.0.0.0"
    :css-dirs          ["resources/public/css"]}


  :cljsbuild {
    :builds {
      :dev {
        :source-paths ["src/cljs"]
        :figwheel {:on-jsload "gg.zfo.www.core/onreload"}
        :incremental true
        :compiler {
          :main           gg.zfo.www.core
          :output-to      "resources/public/js/gg.zfo.www.js"
          :output-dir     "resources/public/out/dev"
          :asset-path     "out/dev"
          :language-in    :ecmascript5
          :language-out   :ecmascript5
          :optimizations  :none
          :elide-asserts  false
          :pretty-print   true
          :source-map     "resources/public/js/gg.zfo.www.js.map"
          :output-wrapper false}}

      :min {
        :source-paths ["src/cljs"]
        :incremental false
        :compiler {
          :main           gg.zfo.www.core
          :output-to      "resources/public/js/gg.zfo.www.js"
          :output-dir     "resources/public/out/min"
          :asset-path     "out/min"
          :language-in    :ecmascript5
          :language-out   :ecmascript5
          :optimizations  :advanced
          :elide-asserts  true
          :pretty-print   false
          ;:source-map     "resources/public/js/gg.zfo.www.js.map"
          :output-wrapper true
          :jar            true}}}})

