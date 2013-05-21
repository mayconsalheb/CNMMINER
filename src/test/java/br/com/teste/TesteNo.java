package br.com.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.Na;
import org.junit.Test;

import br.com.cnmminer.bean.LadoSe;
import br.com.cnmminer.bean.Neuronio;

public class TesteNo {

	@Test
	public void test() {
		
		Neuronio no1 = new Neuronio();
		Neuronio no2 = new Neuronio();
		Neuronio no3 = new Neuronio();
		Neuronio no4 = new Neuronio();
		Neuronio no5 = new Neuronio();
		Neuronio no6 = new Neuronio();
		
		LadoSe lado1 = new LadoSe();
		lado1.setEvidencia("S1");
		LadoSe lado2 = new LadoSe();
		lado2.setEvidencia("S2");
		LadoSe lado3 = new LadoSe();
		lado3.setEvidencia("S3");
		LadoSe lado4 = new LadoSe();
		lado4.setEvidencia("S3");
		
		
		
		ArrayList<LadoSe> array1 = new ArrayList<LadoSe>();
		ArrayList<LadoSe> array2 = new ArrayList<LadoSe>();
		ArrayList<LadoSe> array3 = new ArrayList<LadoSe>();
		ArrayList<LadoSe> array4 = new ArrayList<LadoSe>();
		ArrayList<LadoSe> array5 = new ArrayList<LadoSe>();
		ArrayList<LadoSe> array6 = new ArrayList<LadoSe>();
		
		//Array1
		array1.add(lado1);
		
		//Array2
		array2.add(lado1);
		array2.add(lado2);
		
		//Array3
		array3.add(lado2);
		array3.add(lado1);
		
		//Array4
		array4.add(lado1);
//		array4.add(null);
		array4.add(lado3);
		
		//Array5
		array5.add(lado4);
		array5.add(lado1);
		//Array6
		array6.add(lado3);
		array6.add(lado1);
		
		//-----------------
		
		no1.setEvidencias(array1);
		no1.setHipotese("d1");
		
		no2.setEvidencias(array2);
		no2.setHipotese("d1");
		
		no3.setEvidencias(array3);
		no3.setHipotese("d1");
		
		no4.setEvidencias(array4);
		no4.setHipotese("d2");
		
		no5.setEvidencias(array5);
		no5.setHipotese("d2");
		
		no6.setEvidencias(array6);
		no6.setHipotese("d2");
		//-----------------
		
		ArrayList<Neuronio> arrayNo = new ArrayList<Neuronio>();
		arrayNo.add(no1);
		arrayNo.add(no2);
		arrayNo.add(no3);
		arrayNo.add(no4);
//		arrayNo.add(no5);
		
		
		System.out.println(arrayNo.contains(no5));
		Neuronio aux;
		Integer acumulador = 1;
		if(arrayNo.contains(no6)){

			aux = arrayNo.get(arrayNo.indexOf(no6));
			aux.setAcumulador(acumulador+1);
			
			System.out.println(arrayNo.get(arrayNo.indexOf(no6)).getAcumulador());
		}
			
		
		
	}

}
