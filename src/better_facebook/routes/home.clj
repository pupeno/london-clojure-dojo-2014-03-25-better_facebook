(ns better_facebook.routes.home
  (:use compojure.core)
  (:require [better_facebook.views.layout :as layout]
            [better_facebook.util :as util]
            [better_facebook.models.db :refer [create-status get-all-statuses]]
            [ring.util.response :refer [redirect]]))

(defn home-page []
  (layout/render "home.html"))

(defn statuses []
  (layout/render "statuses.html" {:statuses (get-all-statuses)}))

(defroutes
  home-routes
  clojure.pprint/pprint
  (GET "/" req (if (get-in req [:session :noir :user-id])
                 (statuses)
                 (home-page)))
  (POST "/say" req (do
                     (when-let [user_id (get-in req [:session :noir :user-id])]
                       (create-status {:text    (get-in req [:form-params "status"])
                                       :user_id user_id}))
                     (redirect "/"))))
