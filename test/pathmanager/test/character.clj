(ns pathmanager.test.character
  (:use clojure.test
    pathmanager.character))

(deftest test-adjustment
  (testing "1 adjustments"
    (is (= (attrib-adjustment 1) -5)))
  (testing "2 adjustment"
    (is (= (attrib-adjustment 2) -4)))
  (testing "9 adjustment"
    (is (= (attrib-adjustment 9) -1)))
  (testing "10 adjustment"
    (is (= (attrib-adjustment 10) 0)))
  (testing "12 adjustment"
    (is (= (attrib-adjustment 12) 1)))
)
