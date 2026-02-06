package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import java.util.Set;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class RolValidacionPruebas {
	public static void main(String[] args) {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator v = vf.getValidator();

		Rol r = new Rol(null, "PRUEBAasdfasdf", null);
				// "ñañskdjg ñlahs dñlkjg ñlasdhlñfj asdlñgh lñasdj flñkajh sdlñgkh aslñfjlñasdjglñasj dflñhk laskjdg ñlasdh ñglkj asdlñfj aslñdgh ñlaskdj fñlkash dgñlkh asdñlfj aslñdkjg lñash dglñj asdñlgjh aslñdhf ñlaskdj gñlkahs dglñkjas dñflkj asdlñgh ñasldkfj ñalsdkjg ñlasdhg lñasdj fñlaksdj gñlashd ñgljh asdlñfjasdhgñlashd lñgah sdg ñañskdjg ñlahs dñlkjg ñlasdhlñfj asdlñgh lñasdj flñkajh sdlñgkh aslñfjlñasdjglñasj dflñhk laskjdg ñlasdh ñglkj asdlñfj aslñdgh ñlaskdj fñlkash dgñlkh asdñlfj aslñdkjg lñash dglñj asdñlgjh aslñdhf ñlaskdj gñlkahs dglñkjas dñflkj asdlñgh ñasldkfj ñalsdkjg ñlasdhg lñasdj fñlaksdj gñlashd ñgljh asdlñfjasdhgñlashd lñgah sdgñañskdjg ñlahs dñlkjg ñlasdhlñfj asdlñgh lñasdj flñkajh sdlñgkh aslñfjlñasdjglñasj dflñhk laskjdg ñlasdh ñglkj asdlñfj aslñdgh ñlaskdj fñlkash dgñlkh asdñlfj aslñdkjg lñash dglñj asdñlgjh aslñdhf ñlaskdj gñlkahs dglñkjas dñflkj asdlñgh ñasldkfj ñalsdkjg ñlasdhg lñasdj fñlaksdj gñlashd ñgljh asdlñfjasdhgñlashd lñgah sdgñañskdjg ñlahs dñlkjg ñlasdhlñfj asdlñgh lñasdj flñkajh sdlñgkh aslñfjlñasdjglñasj dflñhk laskjdg ñlasdh ñglkj asdlñfj aslñdgh ñlaskdj fñlkash dgñlkh asdñlfj aslñdkjg lñash dglñj asdñlgjh aslñdhf ñlaskdj gñlkahs dglñkjas dñflkj asdlñgh ñasldkfj ñalsdkjg ñlasdhg lñasdj fñlaksdj gñlashd ñgljh asdlñfjasdhgñlashd lñgah sdg");

		Set<ConstraintViolation<Rol>> errores = v.validate(r);

		if (errores.size() > 0) {
			System.out.println("Se han detectado los siguientes errores");

			for (ConstraintViolation<Rol> error : errores) {
				System.out.println(error.getPropertyPath() + ": " + error.getMessage());
			}
		} else {
			System.out.println("Todo perfecto");
		}
	}
}
