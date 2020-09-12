FROM python:3

RUN pip install --upgrade pip
RUN pip install tweepy python-dateutil simplejson
RUN pip install --upgrade google-cloud-pubsub
RUN pip install --upgrade oauth2client

COPY ccc-2020-289323-1245db651f9b.json /ccc-2020-289323-1245db651f9b.json
COPY twitter-to-pubsub.py /twitter-to-pubsub.py

CMD python twitter-to-pubsub.py
