package org.gozhe.android.cgt.activity.gps;

import java.util.ArrayList;
import java.util.Random;

import org.gozhe.android.R;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.Layer;
import com.esri.android.map.MapOnTouchListener;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnLongPressListener;

import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.MultiPath;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.FeatureSet;
import com.esri.core.map.Graphic;
import com.esri.core.renderer.SimpleRenderer;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.STYLE;
import com.esri.core.symbol.Symbol;
import com.esri.core.tasks.SpatialRelationship;
import com.esri.core.tasks.ags.identify.IdentifyParameters;
import com.esri.core.tasks.ags.identify.IdentifyResult;
import com.esri.core.tasks.ags.identify.IdentifyTask;
import com.esri.core.tasks.ags.query.Query;
import com.esri.core.tasks.ags.query.QueryTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity1 extends Activity {

	MapView mMapView = null;
	ArcGISTiledMapServiceLayer tileLayer;
	ArcGISDynamicMapServiceLayer myLayer;
	MyTouchListener myListener;
	String restUrl = "";
	GraphicsLayer graphicsLayer;
	Graphic[] highlightGraphics;
	
	ProgressDialog progress;
	
	Button btn_draw,btn_query,btn_gp,btn_edit;
	
	String cgtURL="http://192.168.1.104:8399/arcgis/rest/services/CGT/MapServer";
	String mapURL = "http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/PublicSafety/PublicSafetyBasemap/MapServer";
	
	final String[] geometryTypes = new String[] { "Point", "Polyline","Polygon" };
	final String[] gueryTypes = new String[] { "空间查询", "属性查询","要素识别" };
	final String[] layerNames = new String[] {"高速路","区县级行政区划","五级面状水系","综合医院","道路附属设施"};
	final int[] layerIndexes = new int[] { 54,57,56,31,37};
	
	final String[] eidtTypes = new String[] { "Point", "Polyline","Polygon" };

	ArrayList<IdentifyResult> identifyResults;
	 
	String queryType="";
	String selectdLayer="";
	int  selectedLayerIndex=-1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		setContentView(R.layout.map);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_b);
		
	    TextView tv_title=(TextView)findViewById(R.id.tv_title_info);
	    
	    tv_title.setText("地图显示");
	    
		mMapView = (MapView) findViewById(R.id.map);
		
		/* create a @ArcGISTiledMapServiceLayer */
		tileLayer = new ArcGISTiledMapServiceLayer(mapURL);
		
		/* create a @ArcGISDynamicMapServiceLayer */
		myLayer = new ArcGISDynamicMapServiceLayer(cgtURL);
		// Add tiled layer to MapView
		mMapView.addLayer(myLayer);
		
			
		graphicsLayer =  new GraphicsLayer();
		mMapView.addLayer(graphicsLayer);
		myListener = new MyTouchListener(MapActivity1.this, mMapView);
		
		mMapView.setOnTouchListener(myListener);
		
		Point centerPt = mMapView.toMapPoint((float)122.96, (float)39.68);
		
		mMapView.centerAt(centerPt, true);
		//mMapView.zoomToScale(centerPt, 1/100000);
		
		btn_draw=(Button)findViewById(R.id.map_btn_draw);
		btn_draw.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDrawDialog();
			}
		});
		
		btn_query=(Button)findViewById(R.id.map_btn_query);
		btn_query.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showQueryDialog();
			}
			
		});
		
		btn_gp=(Button)findViewById(R.id.map_btn_gp);
		btn_gp.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				queryType="GP_Buffer";
			}
			
		});
		
		btn_edit=(Button)findViewById(R.id.map_btn_edit);
		btn_edit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		Layer[] layers = mMapView.getLayers();
		
		mMapView.setOnLongPressListener(this.longPressListener);
	}
	
	
	private OnLongPressListener longPressListener = new OnLongPressListener(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void onLongPress(float lx, float ly) {
			// TODO Auto-generated method stub
			
			graphicsLayer.removeAll();
			
			if(queryType.equals("空间查询")){
			
				Point pt = mMapView.toMapPoint(lx, ly);		
				String targetLayer = cgtURL.concat("/57");//区县级行政区划
				String[] queryParams = { targetLayer, String.valueOf(lx),String.valueOf(ly)};
				AsyncQueryTask1 ayncQuery = new AsyncQueryTask1();
				ayncQuery.execute(queryParams);
			}
			
			else if(queryType.equals("要素识别")){
				Point pointClicked = mMapView.toMapPoint(lx, ly);

	            /*
	             * Set parameters for identify task
	             */
	            IdentifyParameters inputParameters = new IdentifyParameters();
	            inputParameters.setGeometry(pointClicked);
	            inputParameters.setLayers(new int[] {selectedLayerIndex});
	            Envelope env = new Envelope();
	            mMapView.getExtent().queryEnvelope(env);
				inputParameters.setSpatialReference(mMapView.getSpatialReference());
				inputParameters.setMapExtent(env);
	            inputParameters.setDPI(160);//mdpi
	            inputParameters.setMapHeight(mMapView.getHeight());
	            inputParameters.setMapWidth(mMapView.getWidth());
	            inputParameters.setTolerance(10);

	            /*
	             * Execute identify task
	             */
	            MyIdentifyTask mIdenitfy = new MyIdentifyTask();
	            mIdenitfy.execute(inputParameters); 
			}
			
			else if(queryType.equals("GP_Buffer")){
				Point mp = mMapView.toMapPoint(lx, ly);
				Polygon pg = GeometryEngine.buffer(mp, mMapView.getSpatialReference(), 1000000, null);//NULL--地图单位
				
				//Geometry geo = GeometryEngine.clip(pg, mMapView.getExtent(),mMapView.getSpatialReference());
				
				SimpleFillSymbol sfs = new SimpleFillSymbol(Color.RED);
                sfs.setAlpha(75);
                
				Graphic g = new Graphic(pg,sfs);
				
				graphicsLayer.addGraphic(g);
			}
	
		}
		
	};
	
	
	private void showDrawDialog(){
		new AlertDialog.Builder(MapActivity1.this)
			.setTitle("选择类型")
			.setItems(geometryTypes, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					graphicsLayer.removeAll();
					String geomType = geometryTypes[which];
					if (geomType.equalsIgnoreCase("Polygon")) {
						myListener.setType("POLYGON");
						
					} else if (geomType.equalsIgnoreCase("Polyline")) {
						myListener.setType("POLYLINE");
						
					} else if (geomType.equalsIgnoreCase("Point")) {
						myListener.setType("POINT");
						
					}
				}
			}).create().show();
	}
	
	private void showQueryDialog(){
		new AlertDialog.Builder(MapActivity1.this)
			.setTitle("选择类型")
			.setItems(gueryTypes, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					graphicsLayer.removeAll();
					queryType = gueryTypes[which];
					
					if (queryType.equalsIgnoreCase("空间查询")) {
						Toast.makeText(MapActivity1.this, "长按地图查询", Toast.LENGTH_SHORT).show();
						
					} else if (queryType.equalsIgnoreCase("属性查询")) {
						String targetLayer = cgtURL.concat("/57");//区县级行政区划
						String[] queryParams = { targetLayer, "FID > 2" };
						AsyncQueryTask2 ayncQuery = new AsyncQueryTask2();
						ayncQuery.execute(queryParams);
						
					} else if (queryType.equalsIgnoreCase("要素识别")) {
						
						showIdentifyDialog();
						
						Toast.makeText(MapActivity1.this, "长按地图识别", Toast.LENGTH_SHORT).show();
					}
				}
			}).create().show();
	}
	
	private void showIdentifyDialog(){
		new AlertDialog.Builder(MapActivity1.this)
			.setTitle("选择图层")
			.setItems(layerNames, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					graphicsLayer.removeAll();
				    selectdLayer = layerNames[which];
				    selectedLayerIndex = layerIndexes[which];
				    
				    Toast toast1 = Toast.makeText(getApplicationContext(), selectdLayer,
			                Toast.LENGTH_LONG);
			        toast1.setGravity(Gravity.BOTTOM, 0, 0);
			        toast1.show();

				    Toast toast = Toast.makeText(getApplicationContext(), "Identify features by pressing for 2-3 seconds.",
			                Toast.LENGTH_LONG);
			        toast.setGravity(Gravity.BOTTOM, 0, 0);
			        toast.show();
				}
			}).create().show();
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		mMapView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mMapView.unpause();
	}
	
	
	//------------------------------------------------------------------//
	
	private class AsyncQueryTask1 extends AsyncTask<String, Void, FeatureSet>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progress = ProgressDialog.show(MapActivity1.this, "",
					"Please wait....query task is executing");
			
			super.onPreExecute();
		}
		
		@Override
		protected FeatureSet doInBackground(String... params) {
			// TODO Auto-generated method stub
			if (params == null || params.length <= 1)
				return null;
			
			String url = params[0];
			String lx = params[1];
			String ly = params[2];
			Query query = new Query();

			Point pt = new Point(Float.valueOf(lx),Float.valueOf(ly));
			
			query.setGeometry(pt);
			query.setReturnGeometry(true);
			query.setSpatialRelationship(SpatialRelationship.INTERSECTS);
			
			QueryTask qTask = new QueryTask(url);
			FeatureSet fs = null;
			
			try {
				fs = qTask.execute(query);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return fs;
			}
			return fs;
		}
			
		@Override
		protected void onPostExecute(FeatureSet result) {
			// TODO Auto-generated method stub
			if (result != null) {
				Graphic[] grs = result.getGraphics();

				SimpleRenderer sr = new SimpleRenderer(
						new SimpleFillSymbol(Color.RED));
				graphicsLayer.setRenderer(sr);
				
				if (grs.length > 0) {
					graphicsLayer.addGraphics(grs);					
				}

			}
			progress.dismiss();	
		}	
	}
	
	
	private class MyIdentifyTask extends AsyncTask<IdentifyParameters, Void, IdentifyResult[]>{
		IdentifyTask mIdentifyTask;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			mIdentifyTask = new IdentifyTask(cgtURL);
			
			super.onPreExecute();
		}
		

		@Override
		protected IdentifyResult[] doInBackground(IdentifyParameters... params) {
			// TODO Auto-generated method stub
			IdentifyResult[] mResult = null;
			if (params != null && params.length > 0) {
				IdentifyParameters mParams = params[0];
				try {
					mResult = mIdentifyTask.execute(mParams);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
			return mResult;
		}
		
		@Override
		protected void onPostExecute(IdentifyResult[] results) {
			// TODO Auto-generated method stub
			if (results != null && results.length > 0) {
				 highlightGraphics = new Graphic[results.length];
				 Toast toast = Toast.makeText(getApplicationContext(), results.length + " features identified\n",
		                  Toast.LENGTH_LONG);
		         toast.setGravity(Gravity.BOTTOM, 0, 0);
		         toast.show();
		         for (int i = 0; i < results.length; i++) {
		        	 Geometry geom = results[i].getGeometry();
		             String typeName = geom.getType().name();
		             Random r = new Random();
		             int color = Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		             if (typeName.equalsIgnoreCase("point")) {
		                  SimpleMarkerSymbol sms = new SimpleMarkerSymbol(color, 20, STYLE.SQUARE);
		                  highlightGraphics[i] = new Graphic(geom, sms);
		             } else if (typeName.equalsIgnoreCase("polyline")) {
		                  SimpleLineSymbol sls = new SimpleLineSymbol(color, 5);
		                  highlightGraphics[i] = new Graphic(geom, sls);
		             } else if (typeName.equalsIgnoreCase("polygon")) {
		                  SimpleFillSymbol sfs = new SimpleFillSymbol(color);
		                  sfs.setAlpha(75);
		                  highlightGraphics[i] = new Graphic(geom, sfs);
		             }
		             
		             /**
		               * set the Graphic's geometry, add it to GraphicLayer and refresh the Graphic Layer
		               */
		             graphicsLayer.addGraphic(highlightGraphics[i]);
		         }
			}else{
				 Toast toast = Toast.makeText(getApplicationContext(), "No features identified.", Toast.LENGTH_SHORT);
	             toast.show();
			}
			super.onPostExecute(results);
		}
		
	}
	/**
	 * 
	 * Query Task executes asynchronously.
	 * 
	 */
	private class AsyncQueryTask2 extends AsyncTask<String, Void, FeatureSet> {

		protected void onPreExecute() {
			progress = ProgressDialog.show(MapActivity1.this, "",
					"Please wait....query task is executing");

		}

		/**
		 * First member in parameter array is the query URL; second member is
		 * the where clause.
		 */
		protected FeatureSet doInBackground(String... queryParams) {
			if (queryParams == null || queryParams.length <= 1)
				return null;
			
			String url = queryParams[0];
			Query query = new Query();
			String whereClause = queryParams[1];
			SpatialReference sr = SpatialReference.create(4326);
			query.setGeometry(new Envelope(120.97530215999998,38.71983303,123.51892211999998,40.20518997));
			query.setOutSpatialReference(sr);
			query.setReturnGeometry(true);
			query.setWhere(whereClause);

			QueryTask qTask = new QueryTask(url);
			FeatureSet fs = null;

			try {
				fs = qTask.execute(query);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return fs;
			}
			return fs;

		}

		protected void onPostExecute(FeatureSet result) {
		
			if (result != null) {
				Graphic[] grs = result.getGraphics();
				SimpleRenderer sr = new SimpleRenderer(
						new SimpleFillSymbol(Color.RED));
				graphicsLayer.setRenderer(sr);
				
				if (grs.length > 0) {
					graphicsLayer.addGraphics(grs);					
				}

			}
			progress.dismiss();
		}

	}
	
	/*
	 * MapView's touch listener
	 */
	class MyTouchListener extends MapOnTouchListener {
		
		MultiPath poly;
		String type = "";
		Point startPoint = null;

		public MyTouchListener(Context context, MapView view) {
			
			super(context, view);
		}

		public void setType(String geometryType) {
			this.type = geometryType;
		}

		public String getType() {
			return this.type;
		}
		
		/*
		 * Invoked when user single taps on the map view. This event handler
		 * draws a point at user-tapped location, only after "Draw Point" is
		 * selected from Spinner.
		 * 
		 * @see
		 * com.esri.android.map.MapOnTouchListener#onSingleTap(android.view.
		 * MotionEvent)
		 */
		public boolean onSingleTap(MotionEvent e) {
			if (type.length() > 1 && type.equalsIgnoreCase("POINT")) {
				graphicsLayer.removeAll();
				Graphic graphic = new Graphic(mMapView.toMapPoint(new Point(e.getX(), e
						.getY())),new SimpleMarkerSymbol(Color.RED,25,STYLE.CIRCLE));
				//graphic.setGeometry();
				graphicsLayer.addGraphic(graphic);
				
				return true;
			}
			return false;

		}

		/*
		 * Invoked when user drags finger across screen. Polygon or Polyline is
		 * drawn only when right selected is made from Spinner
		 * 
		 * @see
		 * com.esri.android.map.MapOnTouchListener#onDragPointerMove(android
		 * .view.MotionEvent, android.view.MotionEvent)
		 */
		public boolean onDragPointerMove(MotionEvent from, MotionEvent to) {
			if (type.length() > 1
					&& (type.equalsIgnoreCase("POLYLINE") || type
							.equalsIgnoreCase("POLYGON"))) {

				Point mapPt = mMapView.toMapPoint(to.getX(), to.getY());

				/*
				 * if StartPoint is null, create a polyline and start a path.
				 */
				if (startPoint == null) {
					graphicsLayer.removeAll();
					poly = type.equalsIgnoreCase("POLYLINE") ? new Polyline()
							: new Polygon();
					startPoint = mMapView.toMapPoint(from.getX(), from.getY());
					poly.startPath((float) startPoint.getX(),
							(float) startPoint.getY());

					/*
					 * Create a Graphic and add polyline geometry
					 */
					Graphic graphic = new Graphic(startPoint,new SimpleLineSymbol(Color.RED,5));

					/*
					 * add the updated graphic to graphics layer
					 */
					graphicsLayer.addGraphic(graphic);
				}

				poly.lineTo((float) mapPt.getX(), (float) mapPt.getY());
				
				return true;
			}
			return super.onDragPointerMove(from, to);

		}

		@Override
		public boolean onDragPointerUp(MotionEvent from, MotionEvent to) {
			if (type.length() > 1
					&& (type.equalsIgnoreCase("POLYLINE") || type
							.equalsIgnoreCase("POLYGON"))) {

				/*
				 * When user releases finger, add the last point to polyline.
				 */
				if (type.equalsIgnoreCase("POLYGON")) {
					poly.lineTo((float) startPoint.getX(),
							(float) startPoint.getY());
					graphicsLayer.removeAll();
					graphicsLayer.addGraphic(new Graphic(poly,new SimpleFillSymbol(Color.RED)));
					
				}
				graphicsLayer.addGraphic(new Graphic(poly,new SimpleLineSymbol(Color.BLUE,5)));
				startPoint = null;
				
				return true;
			}
			return super.onDragPointerUp(from, to);
		}
	}
	
}
