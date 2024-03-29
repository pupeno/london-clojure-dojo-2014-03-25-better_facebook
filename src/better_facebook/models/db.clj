(ns better_facebook.models.db
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [better_facebook.models.schema :as schema]))

(defdb db schema/db-spec)

(defentity users)

(defn create-user [user]
  (insert users
          (values user)))

(defn update-user [id first-name last-name email]
  (update users
          (set-fields {:first_name first-name
                       :last_name  last-name
                       :email      email})
          (where {:id id})))

(defn get-user [id]
  (first (select users
                 (where {:id id})
                 (limit 1))))

(defn get-all-users []
  (select users))

(defentity statuses)

(defn create-status [status]
  (insert statuses
          (values status)))

(defn get-all-statuses []
  (select statuses (order :id :desc)))



