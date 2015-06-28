
public class cEnums {
	public enum SocketType {
		TCP, UDP, NONSET 
	}
	
	SocketType _SocketType;
	
	public void Set(SocketType st) {
		this._SocketType = st;
	}
 
	public String SocketDetails() {
		switch (_SocketType) {
		case TCP:
			return "TCP";
		case UDP:
			return "UDP";
		case NONSET:
			return "NONSET";
		default:
			return "";
		}
	}
}
