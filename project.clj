(defproject clojoku "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.3.0"]
                 [ring/ring-jetty-adapter "1.3.0"]
                ]
  :main ^:skip-aot clojoku.core
  :target-path "target/%s"
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler clojoku.core/handler}
  :profiles {:uberjar {:aot :all}})
