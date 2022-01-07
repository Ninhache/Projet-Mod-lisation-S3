package model_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.io.PlyReader;

class PlyReaderTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void set_NotExistingFileWithFileName_Test() throws FileNotFoundException {
		PlyReader reader = new PlyReader();
		Exception exception = assertThrows(FileNotFoundException.class, () ->{
			reader.setFile("I_don't_exist_lol");
		});
		
		String expectedMessage = "There's no file existing in the resources directory with the given name";
		String actualMessage = exception.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void set_NotExistingFileWithFileObject_Test() throws FileNotFoundException {
		PlyReader reader = new PlyReader();
		File file = new File("I_don't_exist_either_lol");
		Exception exception = assertThrows(FileNotFoundException.class, () ->{
			reader.setFile(file);
		});
		
		String expectedMessage = "File not found";
		String actualMessage = exception.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
		
	
	}
	
	@Test
	void set_FileInResourceWithFileName_Test() throws FileNotFoundException{
		PlyReader reader = new PlyReader();
		reader.setFile("ant");
		
		assertTrue(reader.getFileName().equals("ant"));
		assertFalse(reader.getFile() == null);
	}
	
	@Test
	void set_FileInResourceWithFileObject_Test() throws Exception {
		PlyReader reader = new PlyReader();
		File file = new File("./exemples/ant.ply");
		reader.setFile(file);
		
		assertTrue(reader.getFileName().equals(file.getName()));
		assertFalse(reader.getFile() == null);
	}
	
	
}
