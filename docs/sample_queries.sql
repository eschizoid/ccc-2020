WITH
  DATA AS (
  SELECT
    SUBSTR(timestamp, 0, 10) AS tweet_timestamp,
    JSON_EXTRACT_ARRAY(SAFE_CONVERT_BYTES_TO_STRING(FROM_BASE64(payload)),
      "$[messages]") AS tweets
  FROM
    `ccc-2020.tweets`)
SELECT
  tweet_timestamp,
  REGEXP_REPLACE(IFNULL(JSON_EXTRACT_SCALAR(tweet_text,
        "$.data.retweeted_status.extended_tweet.full_text"),
      JSON_EXTRACT_SCALAR(tweet_text,
        "$.data.text")), r'\\n|\\t|\\"', '') AS user_text,
  JSON_EXTRACT_SCALAR(tweet_text,
    "$.data.user.screen_name") AS user_screen_nam,
  JSON_EXTRACT_SCALAR(tweet_text,
    "$.data.user.location") AS user_location,
  JSON_EXTRACT_SCALAR(tweet_text,
    "$.data.user.followers_count") AS user_followers_count
FROM
  DATA
CROSS JOIN
  UNNEST(tweets) AS tweet_text