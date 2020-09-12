package ChicagoCloudConference

import java.util

import com.google.api.services.bigquery.model.{TableFieldSchema, TableSchema}
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions
import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.WriteDisposition.WRITE_APPEND
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO
import org.apache.beam.sdk.options.PipelineOptionsFactory
import org.apache.beam.sdk.transforms.ParDo

object Main extends App {

  private val TOPIC = "projects/ccc-2020-289323/topics/tweets"
  private val TABLE = "chicago_cloud_conference.tweets"

  PipelineOptionsFactory.register(classOf[DataflowPipelineOptions])
  val options = PipelineOptionsFactory
    .fromArgs(args: _*)
    .withValidation()
    .as(classOf[DataflowPipelineOptions])

  val pipeline = Pipeline.create(options)

  pipeline
    .apply("Tweets_Read_PubSub",
           PubsubIO
             .readMessagesWithAttributes()
             .fromTopic(TOPIC))
    .apply("Tweets_Extract_Payload",
           ParDo
             .of(new ExtractTweetPayload()))
    .apply(
      "Tweets_Write_BigQuery",
      BigQueryIO
        .writeTableRows()
        .to(s"${options.getProject}:$TABLE")
        .withCreateDisposition(CREATE_IF_NEEDED)
        .withWriteDisposition(WRITE_APPEND)
        .withSchema(getTableSchema)
    )
  pipeline.run()

  private def getTableSchema = {
    val fields = new util.ArrayList[TableFieldSchema]
    fields.add(new TableFieldSchema().setName("timestamp").setType("INTEGER"))
    fields.add(new TableFieldSchema().setName("payload").setType("STRING"))
    new TableSchema().setFields(fields)
  }
}
