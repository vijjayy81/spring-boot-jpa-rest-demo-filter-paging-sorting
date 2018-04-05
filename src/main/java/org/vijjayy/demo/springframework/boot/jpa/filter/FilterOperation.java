package org.vijjayy.demo.springframework.boot.jpa.filter;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Holds Filter Operation for symbol from <br>
 * 
	   <table border="1">
			<tr><td> Symbol   </td><td> Operation                   </td><td>Example filter query param</td>
			<tr><td>eq       </td><td> Equals                     </td><td>city=eq:Sydney	         </td>
			<tr><td>neq      </td><td> Not Equals                 </td><td>country=neq:uk          </td>
			<tr><td>gt       </td><td> Greater Than               </td><td>amount=gt:10000         </td>
			<tr><td>gte      </td><td> Greater Than or equals to  </td><td>amount=gte:10000        </td>
			<tr><td>lt       </td><td> Less Than                  </td><td>amount=lt:10000         </td>
			<tr><td>lte      </td><td> Less Than or equals to     </td><td>amount=lte:10000        </td>
			<tr><td>in       </td><td> IN                         </td><td>country=in:uk, usa, au  </td>
			<tr><td>nin      </td><td> Not IN                     </td><td>country=nin:fr, de, nz  </td>
			<tr><td>btn      </td><td> Between                    </td><td>joiningDate=btn:2018-01-01, 2016-01-01   </td>
			<tr><td>like     </td><td> Like                       </td><td>firstName=like:John     </td>
		</tr>
	  </table> * 
 * 
 * 
 * @author Vijjayy
 *
 */
public enum FilterOperation {

	EQUAL						("eq"),
	NOT_EQUAL					("neq"),
	GREATER_THAN				("gt"),
	GREATER_THAN_OR_EQUAL_TO	("gte"),
	LESS_THAN					("lt"),
	LESSTHAN_OR_EQUAL_TO		("lte"),
	IN							("in"),
	NOT_IN						("nin"),
	BETWEEN						("btn"),
	CONTAINS					("like");
	
	private String value;

	FilterOperation(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	public static FilterOperation fromValue(String value) {
		for (FilterOperation op : FilterOperation.values()) {
			
			//Case insensitive operation name
			if (String.valueOf(op.value).equalsIgnoreCase(value)) {
				return op;
			}
		}
		return null;
	}

}
