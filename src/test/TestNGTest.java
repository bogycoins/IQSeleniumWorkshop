package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTest {

  @BeforeGroups(groups = { "My group" })
  public void BeforeGroups() {
    System.out.println("@BeforeGroups");
  }

  @BeforeClass
  public void BeforeClass() {
    System.out.println("@BeforeClass");
  }

  @Test(groups = { "My group" })
  public void test1() {
    System.out.println("test1");
  }

  @Test
  public void test2() {
    System.out.println("test2");
  }

  @AfterClass
  public void AfterClass() {
    System.out.println("@AfterClass");
  }

  @BeforeMethod
  public void BeforeMethod() {
    System.out.println("@BeforeMethod");
  }

  @AfterMethod
  public void AfterMethod() {
    System.out.println("@AfterMethod");
  }

  @AfterGroups(groups = { "My group" })
  public void AfterGroups() {
    System.out.println("@AfterGroups");
  }
}
