(ns twarc.core-test
  (:require [clojure.test :refer :all]
            [twarc.core :refer :all])
  (:import [org.quartz JobKey]
           [org.quartz.impl.matchers GroupMatcher]))

;;
;; Test Suit Helpers
;;

(defn get-job
  [scheduler task-name task-group]
  (let [scheduler (:scheduler scheduler)
        job-key (JobKey. task-name task-group)
        job-detail (.getJobDetail scheduler job-key)]
    job-detail))

;;
;; Testing job cases
;;

(def scheduler
  (make-scheduler {} { "threadPool.threadCount" 1 }))

(def args ["Andrew" "Rudenko"])
(def state {:db-connection "(.)(.)"})

(defjob simple-job
  [ctx first-name last-name]
  nil)

(simple-job
  scheduler
  args
  :job {:identity "task-1"
        :group "test-suite"})

(defjob statefull-job
  [ctx first-name last-name]
  nil)

(statefull-job
  scheduler
  args
  :job {:identity "task-2"
        :group "test-suite"
        :state state})
;;
;; Testing
;;

(deftest defjob-test
  (testing "Simple job"
    (let [job (get-job scheduler "task-1" "test-suite")
          data-map (.getJobDataMap job)
          args (get data-map "arguments")
          state (get data-map "state")]
      (is (= args args))
      (is (= state nil))))
  (testing "Statefull job"
    (let [job (get-job scheduler "task-2" "test-suite")
          data-map (.getJobDataMap job)
          args (get data-map "arguments")
          state (get data-map "state")]
      (is (= args args))
      (is (= state state)))))
