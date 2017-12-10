import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;

/**
 * Frame of program, do all the task before game starts
 * 
 * @author DongYeop Lee
 *
 */
public class TetrisFrame extends JFrame {

	private MainPanel panel;
	Clip clip;

	TetrisFrame() {
		super();
		// 프레임 전체화면으로 지정
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		this.setUndecorated(true);
		gd.setFullScreenWindow(this);

		// 메인 패널 생성
		panel = new MainPanel(this);

		this.getContentPane().add(panel);

		// 포커스 가능하게 지정
		this.getContentPane().setFocusable(true);

		// TetrisConstants 에 저장된 변수들 이용해서 이미지 크기 리사이징
		for (int i = 0; i < 8; i++) {
			imgResize(TetrisConstants.original_filename[i], TetrisConstants.target_filename[i], "png",
					TetrisConstants.BLOCKSIZE, TetrisConstants.BLOCKSIZE);
		}
		imgResize(TetrisConstants.original_filename[8], TetrisConstants.target_filename[8], "png",
				TetrisConstants.MAP_WIDTH, TetrisConstants.MAP_HEIGHT);
		imgResize(TetrisConstants.original_filename[9], TetrisConstants.target_filename[9], "png",
				TetrisConstants.res.width / 4, TetrisConstants.res.height / 2);
		imgResize(TetrisConstants.original_filename[10], TetrisConstants.target_filename[10], "jpg",
				TetrisConstants.res.width, TetrisConstants.res.height);

		// 음악 파일 받아서 clip에 연결
		try {
			File themeSongFile = new File("./resource/sound/themeA.wav");
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;

			stream = AudioSystem.getAudioInputStream(themeSongFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Method to resize image <br>
	 * <br>
	 * read(imgSourcePath) -> new Image(newWidth x new Height)
	 * ->write(imgTargetPath)
	 * 
	 * @param imgSourcePath
	 * @param imgTargetPath
	 * @param imgFormat
	 * @param newWidth
	 * @param newHeight
	 */
	public void imgResize(String imgSourcePath, String imgTargetPath, String imgFormat, int newWidth, int newHeight) {
		try {

			Image imgSrc = ImageIO.read(new File("./resource/image/" + imgSourcePath)); // 이미지 읽어오기

			Image resizeImage = imgSrc.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); // 이미지 크기 변경

			// 새 이미지 저장하기
			BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
			// 새로운 BufferedImage 인스턴스에 새로운 이미지 저장
			Graphics g = newImage.getGraphics();
			g.drawImage(resizeImage, 0, 0, null); // 그래픽스 인스턴스에 바뀐 이미지 그리기
			g.dispose(); // 그래픽스 인스턴스 종료
			ImageIO.write(newImage, imgFormat, new File("./resource/image/" + imgTargetPath)); // 새로운 이미지 파일에 쓰기
		} catch (Exception e) {
		}
	}

	/**
	 * play the song in the clip
	 * 
	 */
	public void startTheme() {
		try {
			clip.start(); // 시작
			clip.loop(Clip.LOOP_CONTINUOUSLY); // 무한반복
		} catch (Exception e) {

		}
	}

	/**
	 * stop the song in the clip
	 */
	public void stopTheme() {
		clip.stop();
	}
}
