(defproject clojoku "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.3.0"]
                 [ring/ring-jetty-adapter "1.3.0"]
                 [compojure "1.1.6"]
                 [metosin/compojure-api "0.16.2"]
                ]
  :main ^:skip-aot clojoku.core
  :target-path "target/%s"
  :plugins [[lein-ring "0.8.11"] [lein-beanstalk "0.2.7"]]
  :ring {:handler clojoku.core/app}
  :profiles {:uberjar {:aot :all}}
  :aws {:beanstalk {:region "us-east-1" }}
)
