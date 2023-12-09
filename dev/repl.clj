(ns repl
  (:require [wing.repl :as repl]))

(comment
 ;; Sync libraries in deps.edn including these defined in :repl alias.
 (repl/sync-libs! :repl))
