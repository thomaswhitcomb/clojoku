(defproject clojoku "0.1.0"
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
                 [com.amazonaws/aws-lambda-java-core "1.0.0"]
                ]
  :main ^:skip-aot clojoku.core
  :target-path "target/%s"
  :plugins [[lein-ring "0.8.11"] [lein-beanstalk "0.2.7"]]
  :ring {:handler clojoku.core/app}
  :profiles {:uberjar {:aot :all}}

  :aws {:beanstalk {:region "us-east-1"
                    :environments [{:name "development"
                                    :stack {:name "clojoku-stack"
                                    :options { "aws:autoscaling:launchconfiguration" {"IamInstanceProfile" "RootInstanceProfile" "SecurityGroups" "BeanstalkSecurityGroup"} "aws:ec2:vpc" {"VpcId" "VPC" "Subnets" "PrivateSubnet"}
                                      }
                                    }
                                    :options {"aws:autoscaling:launchconfiguration"
                                             {"EC2KeyName" "toms-cap-pre-prod"
                                              "ImageId" "ami-1a249873"}}}
                                   ]
                    }
        }
)
