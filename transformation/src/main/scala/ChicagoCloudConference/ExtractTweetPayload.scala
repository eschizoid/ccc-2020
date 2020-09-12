package ChicagoCloudConference

import java.nio.charset.StandardCharsets

import com.google.api.services.bigquery.model.TableRow
import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage
import org.apache.beam.sdk.transforms.DoFn
import org.apache.beam.sdk.transforms.DoFn.ProcessElement

class ExtractTweetPayload extends DoFn[PubsubMessage, TableRow] {
  @ProcessElement
  def processElement(c: ProcessContext): Unit = {
    val payload = new String(c.element.getPayload, StandardCharsets.UTF_8)
    c.output(
      new TableRow()
        .set("timestamp", System.currentTimeMillis)
        .set("payload", payload.getBytes("utf-8")))
  }
}
