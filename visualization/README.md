## Visualization
The purpose of this project is to do some sentiment analysis on tweets stored in BigQuery.

### Running Jupyter infrastructure
```shell script
$ ./bin/deploy_visualization.sh
```

### Notebook Pre-requisites:
After the deployment is done we need to perform the following task before doing the sentiment analysis:

1. Export BiqQuery dataset using the `SELECT` in the main README file
2. Give permissions to the CSV file
3. Execute notebook 
