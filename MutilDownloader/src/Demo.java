import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ���߳����ز�����
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

				// �����ͷ��������ļ���Сͬ�������ʱ�ļ�
				RandomAccessFile raf = new RandomAccessFile("setup3.exe", "rwd");
				raf.setLength(length);
				raf.close();
				// if there are three thread go go to download resource
				// get every thread �����ļ���С
				int blockSize = length / threadCount;
				for (int threadId = 1; threadId <= threadCount; threadId++) {
					// ��һ���سǵĿ�ʼλ��
					int startindex = (threadId - 1) * blockSize;
					int endindex = threadId * blockSize - 1;

					if (threadId == threadCount) {
						// ���һ���߳����ڵĳ���
						endindex = length;
					}
					new DownloadThread(threadId, startindex, endindex, path)
							.start();

				}
			} else {
				System.out.println("�ļ�����");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * �����ļ������̣߳�ÿ���߳����ض�Ӧλ�õ��ļ�
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

				// check �Ƿ���ڼ�¼���س��ȵ��ļ�����ȡ��
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
				System.out.println("�߳�:" + threadid + " ��ʵ����λ�ã�" + startindex
						+ "----->" + endindex);
				int code = conn.getResponseCode();// �ӷ���������ȫ����Դ
													// ����200�����󲿷���Դ����206
				// ������������ز�����Դ

				System.out.println("code = " + code);
				InputStream is = conn.getInputStream();// �Ѿ������������λ�ã����ص�ǰλ�ö�Ӧ�ļ���������
				// ʹ������ļ�������
				RandomAccessFile raf = new RandomAccessFile("setup3.exe", "rwd");

				// ��λд�ļ��Ŀ�ʼλ��
				raf.seek(startindex);
				int len = 0;
				byte[] buffer = new byte[1024];
				int total =0;//�Ѿ����ص�λ��
				while ((len = is.read(buffer)) != -1) {
					RandomAccessFile file = new RandomAccessFile(threadid+".txt", "rwd");
					raf.write(buffer, 0, len);
					total+=len;
					System.out.println("�̣߳�"+threadid+" total:"+(total+startindex));
					file.write((""+total+startindex).getBytes());//��¼����λ��
					file.close();
				
				}
				is.close();
				raf.close();
				System.out.println("�߳�: " + threadid + "  ��������");

				
				
			} catch (Exception e) {

				e.printStackTrace();
			}finally{
				threadFinship();
			}
		}

		private synchronized void  threadFinship() {
			//�ж������߳��Ѿ�������ϣ�ɾ���ļ�
			runningThread--;
			if(runningThread==0){
				for(int i =1;i<=3;i++){
					File deleteFile = new File(i+".txt");
					deleteFile.delete();
				}
				System.out.println("�ļ�������ϣ�ɾ���������ؼ�¼");
			}
		}

	}
}
