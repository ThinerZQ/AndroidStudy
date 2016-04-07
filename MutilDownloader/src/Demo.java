import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 多线程下载测试类
 * 
 * @author Administrator
 * 
 */
public class Demo {

	public static int threadCount = 3;
	public static int runningThread =3;

	public static void main(String[] args) {
		// 1 connection to server ,get a file and file's length and create a
		// local temp file which is same with server file
		String path = "http://192.168.76.1:8080/360cse_7-5-2-119.exe";
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			System.out.println("code=========" + code);
			if (code == 200) {
				// server client response data's length,actually it is file's
				// length
				int length = conn.getContentLength();

				// 创建和服务器端文件大小同样大的临时文件
				RandomAccessFile raf = new RandomAccessFile("setup3.exe", "rwd");
				raf.setLength(length);
				raf.close();
				// if there are three thread go go to download resource
				// get every thread 下载文件大小
				int blockSize = length / threadCount;
				for (int threadId = 1; threadId <= threadCount; threadId++) {
					// 第一个县城的开始位置
					int startindex = (threadId - 1) * blockSize;
					int endindex = threadId * blockSize - 1;

					if (threadId == threadCount) {
						// 最后一个线程现在的长度
						endindex = length;
					}
					new DownloadThread(threadId, startindex, endindex, path)
							.start();

				}
			} else {
				System.out.println("文件出错");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 下载文件的子线程，每个线程下载对应位置的文件
	 * 
	 * @author Administrator
	 * 
	 */
	public static class DownloadThread extends Thread {

		private int threadid;
		private int startindex;
		private int endindex;
		private String path;

		public DownloadThread(int threadid, int startindex, int endindex,
				String path) {

			this.threadid = threadid;
			this.startindex = startindex;
			this.endindex = endindex;
			this.path = path;
		}

		@Override
		public void run() {
			try {

				// check 是否存在记录下载长度的文件，读取它
				File tempfile = new File(threadid + ".txt");
				if (tempfile.exists() && tempfile.length() > 0) {
					FileInputStream fis = new FileInputStream(tempfile);
					byte[] temp = new byte[1024];
					int leng = fis.read(temp);
					String downleng = new String(temp, 0, leng);
					int downloadlenInt = Integer.parseInt(downleng);
					startindex = downloadlenInt;// update download index
					fis.close();
				}
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();

				conn.setRequestMethod("GET");
				conn.setRequestProperty("Range", "bytes=" + startindex + "-"
						+ endindex + "");
				conn.setReadTimeout(5000);
				System.out.println("线程:" + threadid + " 真实下载位置：" + startindex
						+ "----->" + endindex);
				int code = conn.getResponseCode();// 从服务器请求全部资源
													// 返回200，请求部分资源返回206
				// 请求服务器下载部分资源

				System.out.println("code = " + code);
				InputStream is = conn.getInputStream();// 已经设置了请求的位置，返回当前位置对应文件的输入流
				// 使用随机文件访问类
				RandomAccessFile raf = new RandomAccessFile("setup3.exe", "rwd");

				// 定位写文件的开始位置
				raf.seek(startindex);
				int len = 0;
				byte[] buffer = new byte[1024];
				int total =0;//已经下载的位置
				while ((len = is.read(buffer)) != -1) {
					RandomAccessFile file = new RandomAccessFile(threadid+".txt", "rwd");
					raf.write(buffer, 0, len);
					total+=len;
					System.out.println("线程："+threadid+" total:"+(total+startindex));
					file.write((""+total+startindex).getBytes());//记录下载位置
					file.close();
				
				}
				is.close();
				raf.close();
				System.out.println("线程: " + threadid + "  下载完了");

				
				
			} catch (Exception e) {

				e.printStackTrace();
			}finally{
				threadFinship();
			}
		}

		private synchronized void  threadFinship() {
			//判断所有线程已经下载完毕，删除文件
			runningThread--;
			if(runningThread==0){
				for(int i =1;i<=3;i++){
					File deleteFile = new File(i+".txt");
					deleteFile.delete();
				}
				System.out.println("文件下载完毕，删除所有下载记录");
			}
		}

	}
}
