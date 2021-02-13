package domain;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import domain.atom.Atom;
import domain.atom.AtomFactory;
import domain.molecule.MoleculeFactory;
import domain.powerup.Powerup;
import domain.powerup.PowerupFactory;
import domain.reactionBlocker.BlockerFactory;
import domain.reactionBlocker.ReactionBlocker;
import ui.UIAtom;
import ui.UIGameObject;
import ui.UIMoleculeFactory;
import ui.UIPowerup;
import ui.UIReactionBlocker;
import ui.molecule.UIMolecule;
import org.bson.Document;
import org.bson.conversions.Bson;

public class SaveDatabase implements ISaveLoad{
	String username="",currentShootingObject="";
	double objectMovementAngle=0,objectX=0,objectY=0, score=0;
	int lengthL=0,remainingTime=0, alphaAtomCount=0, betaAtomCount=0,sigmaAtomCount=0,gammaAtomCount=0, 
	alphaPUCount=0,betaPUCount=0,sigmaPUCount=0,gammaPUCount=0,
	etaCount= 0, lotaCount= 0, thetaCount = 0, zetaCount =0,health=100;
	ArrayList<GameObject> list;
	Controller controller;
	boolean isShooted;
	SaveFile instance;
	double speed;
	double diameter= Controller.L/4;
	MongoCollection<Document> collection;
	public SaveDatabase(Controller controller) {
		this.controller= controller;
		this.username=controller.getUsername();
		this.score=controller.getScore();
		this.remainingTime= controller.getTime();
		this.speed= controller.getSpeed();
		this.etaCount=controller.getEtaCount();
		this.lotaCount=controller.getLotaCount();
		this.thetaCount= controller.getThetaCount();
		this.zetaCount= controller.getZetaCount();
		this.alphaAtomCount= controller.getAlphaCount();
		this.betaAtomCount= controller.getBetaCount();
		this.sigmaAtomCount= controller.getSigmaCount();
		this.gammaAtomCount= controller.getGammaCount();
		this.alphaPUCount= controller.getAlphaPUCount();
		this.betaPUCount= controller.getBetaPUCount();
		this.sigmaPUCount= controller.getSigmaPUCount();
		this.gammaPUCount= controller.getGammaPUCount();
		this.lengthL= controller.getLengthL();
		this.health= controller.getHealth();
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.	
		MongoClient mongoClient = MongoClients.create("mongodb+srv://comp302_user:comp302_password@sandbox.v2mqr.mongodb.net/");
	      // Creating Credentials 
//	      MongoCredential credential; 
//	      credential = MongoCredential.createCredential("mehmet", "TeamPass", 
//	         "mehmet".toCharArray()); 
		MongoDatabase database = mongoClient.getDatabase("Comp302"); // selecting the database 
		System.out.println("asaad");
//		database.createCollection("KuVid");
		
		collection = database.getCollection("TeamPassCollection"); // collection
		
	}
	public void saveGame() {
		
		
		
		
		Document doc = new Document();
		 //dumping game info to the collection
		doc.append("username", username);
		doc.append("score", score);
		doc.append("speed", speed);
		doc.append("health", health);
		//Remaining time as seconds.
		doc.append("remainingTime", remainingTime);
		lengthL= controller.getLengthL();
		doc.append("lengthL", lengthL);
		//Shooting Object
		currentShootingObject= controller.getShootingObject().getType();
		isShooted= controller.getShootingObject().isShooted();
		objectX = controller.getShootingObject().getX();
		objectY = controller.getShootingObject().getY();
		objectMovementAngle= controller.getShootingObject().getRotationAngle();
		doc.append("currentShootingObject", currentShootingObject);
		doc.append("objectX", objectX);
		doc.append("objectY", objectY);
		doc.append("isShooted", isShooted);
		
		//Movement angle double.
		doc.append("objectMovementAngle", objectMovementAngle);
		
		
		//Atoms
		doc.append("alphaAtom", alphaAtomCount);
		doc.append("betaAtom", betaAtomCount);
		doc.append("sigmaAtom", sigmaAtomCount);
		doc.append("gammaAtom", gammaAtomCount);
		
		//Powerups
		doc.append("alphaPU", alphaPUCount);
		doc.append("betaPU", betaPUCount);
		doc.append("sigmaPU", sigmaPUCount);
		doc.append("gammaPU", gammaPUCount);
		
		//Shields
		doc.append("eta", etaCount);
		doc.append("lota", lotaCount);
		doc.append("theta", thetaCount);
		doc.append("zeta", zetaCount);
		 
	     // saving document to the collection i.e., KuvidCollection
		
		
		
		if(list==null) {
			list = new ArrayList<GameObject>();
		}
		for(int i=2;i<controller.objects.size();i++) {
			list.add(controller.objects.get(i));
		}
		// Molecule and Powerup & Reaction Blockers
		for(int i=0;i<list.size();i++) {
			doc.append("type"+i, list.get(i).getType());
			doc.append("x"+i, list.get(i).getX());
			doc.append("y"+i, list.get(i).getY());
			doc.append("speed"+i, list.get(i).getSpeed());
		}
		Bson filter = eq("username", username);
		DeleteResult result = collection.deleteOne(filter);
		System.out.println(result);
		
		collection.insertOne(doc);
		System.out.println("Document inserted successfully");
		 
         
	}
	
	public void loadGame() {
		Document my_doc = collection.find().first();
		 my_doc = collection.find(eq("username", username)).first(); 
		 try {
			String json = my_doc.toJson();
			System.out.println(json);
			Document document = Document.parse(json);
			System.out.println(document);
			username=document.getString("username");
			score= document.getDouble("score");
			speed= document.getDouble("speed");
			alphaAtomCount= document.getInteger("alphaAtom");
			betaAtomCount= document.getInteger("betaAtom");
			sigmaAtomCount=document.getInteger("sigmaAtom");
			gammaAtomCount= document.getInteger("gammaAtom");
			health = document.getInteger("health");
			
			//Shields
			etaCount= document.getInteger("eta");
			lotaCount= document.getInteger("lota");
			thetaCount= document.getInteger("theta");
			zetaCount= document.getInteger("zeta");
			
			remainingTime= document.getInteger("remainingTime");
			String currentShootingObject= document.getString("currentShootingObject");
//				System.out.println(currentShootingObject);
			double objectMovementAngle= document.getDouble("objectMovementAngle");
			double objectX= document.getDouble("objectX");
			double objectY= document.getDouble("objectY");
			boolean isShooted = document.getBoolean("isShooted");
			GameObject shootingObject= controller.getShootingObject();
			UIGameObject uiobject= controller.getUIShootingObject();
//				System.out.println(currentShootingObject);
			
			//Shields
			controller.setEtaCount(etaCount);
			controller.setLotaCount(lotaCount);
			controller.setThetaCount(thetaCount);
			controller.setZetaCount(zetaCount);
			
			//Time
			controller.setTime(remainingTime);
			controller.setSpeed(speed);
			controller.setScore(score);
			controller.setHealth(health);
//				controller.setLengthL(lengthL);
			
			//Shooting Object load
			if(currentShootingObject.equals("alpha") || currentShootingObject.equals("beta") || currentShootingObject.equals("sigma") || currentShootingObject.equals("gamma")) {
				Atom atom1= AtomFactory.getAtom((Atom) shootingObject,currentShootingObject);
				((UIAtom) uiobject).setAtomType(atom1.getType());
			}else if(currentShootingObject.equals("+alpha") || currentShootingObject.equals("+beta") || currentShootingObject.equals("+sigma") || currentShootingObject.equals("+gamma")) {
				controller.switchToPowerup(currentShootingObject);
//					Powerup pu1= PowerupFactory.getPU((Powerup) controller.getShootingObject(),currentShootingObject);
			}
			controller.getShootingObject().setShooted(isShooted);
			controller.getShootingObject().setX(objectX);
			controller.getShootingObject().setY(objectY);
			controller.getShootingObject().setRotationAngle(objectMovementAngle);
			
			for(int i=0;i<document.size();i++) {
				String s= document.getString("type"+i);
				if(s!=null) {
					if(isMolecule(s)) {
						GameObject molecule = MoleculeFactory.getMolecule(document.getString("type"+i));
						molecule.setX(document.getDouble("x"+i));
						molecule.setY(document.getDouble("y"+i));
						molecule.setWidth(diameter*3);
						molecule.setHeight(diameter*3);
						controller.objects.add(molecule);
						
						UIMolecule uimolecule = UIMoleculeFactory.getMolecule(document.getString("type"+i));
						uimolecule.setWidth(molecule.getWidth());
						uimolecule.setHeight(molecule.getHeight());
						uimolecule.setX(molecule.getX());
						uimolecule.setY(molecule.getY());
						controller.getRenderer().objects.add(uimolecule);
					}
					else if(isPowerup(s)){
						Powerup pu = PowerupFactory.getPU(s);
						pu.setX(document.getDouble("x"+i));
						pu.setY(document.getDouble("y"+i));
						pu.setWidth(diameter*3);
						pu.setHeight(diameter*3);
//							pu.setRotationAngle(0);
						pu.setSpeed(document.getDouble("speed"+i));
//							System.out.println(pu.getSpeed());
						controller.addObject(pu);
						UIPowerup uiPu = new UIPowerup(pu.getType());
						uiPu.setWidth(pu.getWidth());
						uiPu.setHeight(pu.getHeight());
						controller.renderer.objects.add(uiPu);
					}
					else if(isBlocker(s)){
						ReactionBlocker blocker = BlockerFactory.getBlocker(s);
						blocker.setX(document.getDouble("x"+i));
						blocker.setY(document.getDouble("y"+i));
						blocker.setWidth(diameter*3);
						blocker.setHeight(diameter*3);
						blocker.setSpeed(document.getDouble("speed"+i));
						controller.addObject(blocker);
						UIReactionBlocker uiBlocker = new UIReactionBlocker(blocker.getType());
						uiBlocker.setWidth(blocker.getWidth());
						uiBlocker.setHeight(blocker.getHeight());
						controller.renderer.objects.add(uiBlocker);
					}
				}
				
				
			}
			
			// Loading the Atom counts on the game.
			controller.setAlphaCount(alphaAtomCount);
			controller.setBetaCount(betaAtomCount);
			controller.setSigmaCount(sigmaAtomCount);
			controller.setGammaCount(gammaAtomCount);
	        } catch (NullPointerException e) {
	            System.out.println(e); 
	      }
		
		 
		
	}
	private boolean isMolecule(String s) {
		if(s.equals("AlphaMolecule") || s.equals("BetaMolecule") || s.equals("SigmaMolecule") || s.equals("GammaMolecule")) {
			return true;
		}
		return false;
	}
	private boolean isPowerup(String s) {
		if(s.equals("+alpha") || s.equals("+beta") || s.equals("+sigma") || s.equals("+gamma")) {
			return true;
		}
		return false;
	}
	private boolean isBlocker(String s) {
		if(s.equals("alpha") || s.equals("beta") || s.equals("sigma") || s.equals("gamma")) {
			return true;
		}
		return false;
	}
}
