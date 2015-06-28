
public class cDataManager {

	private byte[] _ID;
	private byte[] _value;
	private cEnums.SocketType socketType;
	
	public cDataManager(){
		_ID = null;
		_value = null;
		socketType = cEnums.SocketType.NONSET; 
	}
	
	public byte[] getID(){ return this._ID; }
	public byte[] getValue(){ return this._value; }
	
	public cDataManager(byte[] data, cEnums.SocketType st){
		
		socketType = st;
		if (socketType == cEnums.SocketType.TCP){
			this._ID = cUtils.GetBytes(data, 0, 16);
			this._value = cUtils.GetBytes(data, 16, 18);
		}

		if (socketType == cEnums.SocketType.UDP){
			this._ID = cUtils.GetBytes(data, 2, 4);
			this._value = cUtils.GetBytes(data, 0, 2);
		}

		if (socketType == cEnums.SocketType.NONSET){
			this._ID = null;
			this._value = null;
		}
	}
	
	public boolean SaveRecord(){
		return false;
		
	}
}
