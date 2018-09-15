package study.thrift.invoker;

import java.util.concurrent.CountDownLatch;
import org.apache.thrift.async.AsyncMethodCallback;
import thrift.gencode.server.HelloService;

/**
 * Created by admin on 2018/9/15.
 */
public class AsynInvokerCallback implements AsyncMethodCallback<HelloService.AsyncClient.sayHello_call> {

  private CountDownLatch latch;

  public AsynInvokerCallback(CountDownLatch latch) {
    this.latch = latch;
  }

  /**
   * 异步调用完成，回调该方法
   * @param response
   */
  @Override
  public void onComplete(HelloService.AsyncClient.sayHello_call response) {
    try {
      System.out.println("AsynInvokerCallback response: " + response.getResult());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      latch.countDown();
    }
  }

  /**
   * 异步调用出错回调方法
   * @param e
   */
  @Override
  public void onError(Exception e) {
    latch.countDown();
  }
}
