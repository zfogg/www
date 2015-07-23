(defproject gg.zfo.www "0.1.0-SNAPSHOT"
  :source-paths ["src/clj"]


  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3211"]

                 [environ "1.0.0"]

                 [lein-environ "1.0.0"]
                 [lein-cljsbuild "1.0.6"]
                 [lein-figwheel "0.3.3"]
                 [lein-less "1.7.5"]

                 [reagent "0.5.0"]
                 [re-frame "0.4.1"]
                 [re-com "0.5.4"]
                 [secretary "1.2.3"]]

  :plugins [[lein-environ "1.0.0"]
            [lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.3" :exclusions [cider/cider-nrepl]]
            [lein-less "1.7.5"]]

  :hooks [leiningen.cljsbuild
          leiningen.less]


  :figwheel {
    :http-server-root  "public"
    :server-port       3449
    :server-ip         "0.0.0.0"
    :css-dirs          ["resources/public/css"]}


  :cljsbuild {
    :builds [
      { :id "dev"
        :source-paths ["src/cljs"]
        :figwheel {:on-jsload "gg.zfo.www.core/onreload"}
        :incremental true
        :compiler {
          :main                 gg.zfo.www.core
          :output-to            "resources/public/js/gg.zfo.www.js"
          :output-dir           "resources/public/.tmp/dev"
          :asset-path           ".tmp/dev"
          :pretty-print         true
          :source-map           true
          :source-map-timestamp true
          :cache-analysis       true
          :compiler-stats       true}}

      { :id "prod" 
        :source-paths ["src/cljs"]
        :incremental false
        :compiler {
          :main            gg.zfo.www.core
          :output-to       "resources/public/js/gg.zfo.www.js"
          :output-dir      "resources/public/.tmp/prod"
          :asset-path      ".tmp/prod"
          :optimizations   :advanced
          :closure-defines {"goog.DEBUG" false}
          :elide-asserts   true
          :pretty-print    false
          :output-wrapper  true
          :static-fns      true
          :jar             true}}]}

  :less {
    :source-paths ["src/less"]
    :target-path "resources/public/css"})

