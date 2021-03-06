(defproject twarc "0.1.1-SNAPSHOT"
  :description "Doing Quartz in right way"
  :url "https://github.com/prepor/twarc"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.quartz-scheduler/quartz "2.2.1"]
                 [org.quartz-scheduler/quartz-jobs "2.2.1"]
                 [org.clojure/tools.logging "0.3.1"]
                 [com.stuartsierra/component "0.2.2"]
                 [prismatic/plumbing "0.3.5"]]
  :profiles {:dev {:dependencies [[org.slf4j/slf4j-api "1.7.7"]
                                  [ch.qos.logback/logback-classic "1.1.2"]
                                  [org.postgresql/postgresql "9.3-1102-jdbc41"]]}}
  :lein-release {:deploy-via :clojars})
