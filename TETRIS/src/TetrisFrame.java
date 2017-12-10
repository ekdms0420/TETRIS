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
		// ������ ��üȭ������ ����
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		this.setUndecorated(true);
		gd.setFullScreenWindow(this);

		// ���� �г� ����
		panel = new MainPanel(this);

		this.getContentPane().add(panel);

		// ��Ŀ�� �����ϰ� ����
		this.getContentPane().setFocusable(true);

		// TetrisConstants �� ����� ������ �̿��ؼ� �̹��� ũ�� ������¡
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

		// ���� ���� �޾Ƽ� clip�� ����
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

			Image imgSrc = ImageIO.read(new File("./resource/image/" + imgSourcePath)); // �̹��� �о����

			Image resizeImage = imgSrc.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); // �̹��� ũ�� ����

			// �� �̹��� �����ϱ�
			BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
			// ���ο� BufferedImage �ν��Ͻ��� ���ο� �̹��� ����
			Graphics g = newImage.getGraphics();
			g.drawImage(resizeImage, 0, 0, null); // �׷��Ƚ� �ν��Ͻ��� �ٲ� �̹��� �׸���
			g.dispose(); // �׷��Ƚ� �ν��Ͻ� ����
			ImageIO.write(newImage, imgFormat, new File("./resource/image/" + imgTargetPath)); // ���ο� �̹��� ���Ͽ� ����
		} catch (Exception e) {
		}
	}

	/**
	 * play the song in the clip
	 * 
	 */
	public void startTheme() {
		try {
			clip.start(); // ����
			clip.loop(Clip.LOOP_CONTINUOUSLY); // ���ѹݺ�
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
