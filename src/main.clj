(ns main
  (:require
   [clojure.string :as str]
   [wkok.openai-clojure.api :as api]))

(defn main []
  (println "Hello"))
#_ (main)

(defn api-key []
  ;;read API KEY from env var, otherwise try reading from pass
  (or (System/getenv "OPENAI_API_KEY")
      (some-> (clojure.java.shell/sh "pass" "rynkowski/openai/playground-231209")
              :out
              (str/trim-newline))))

;; to create an API KEY go to https://platform.openai.com/api-keys

;; If you like to see API here is their API reference:
;; https://github.com/openai/openai-openapi/blob/master/openapi.yaml

;; Here I'm using openai-clojure:
;; https://github.com/wkok/openai-clojure/blob/main/doc/01-usage-openai.md

(def opts
  {:api-key (api-key)})

(comment
 (api/create-chat-completion {:model "gpt-3.5-turbo"
                              :messages [{:role "system" :content "You are a helpful assistant."}
                                         {:role "user" :content "Who won the world series in 2020?"}
                                         {:role "assistant" :content "The Los Angeles Dodgers won the World Series in 2020."}
                                         {:role "user" :content "Where was it played?"}]}
                             opts)

 (api/create-embedding {:input "The food was delicious and the waiter...",
                        :model "text-embedding-ada-002",
                        :encoding-format "float"}
                       opts)

 (api/create-moderation {:input "You shouldn't be born"
                         :model "text-moderation-latest"}
                        opts))
