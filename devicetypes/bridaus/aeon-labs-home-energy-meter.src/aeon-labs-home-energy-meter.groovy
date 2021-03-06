/**
 *  Aeon Labs Home Energy Meter [[This is a DEVICE TYPE]]
 *
 *  Copyright 2015 Brian Gudauskas
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *
 *  Aeon Labs Home Energy Meter (US)
 *
 *  Author: Brian Gudauskas
 *  Contributors: Barry Burke, SmartThings
 *
 *  History:
 *  01/27/2016: Formatting Corrections
 *	11/30/15: Removed required on install for Android bug
 *	11/25/15: Installation Updates
 * 	10/30/15: Updates  
 *		Fixed issue on first install
 *		Added watts threshold
 * 	10/26/15: Updates  
 *		Fixed watts high/low
 *		Fixed reset energy
 *		Flattened all info tiles
 *		Fixed colors for Amps/Volts
 * 	10/22/15: Updates  
 *		Reset now zeros kwH
 *		Tons of cleanup
 * 	10/21/15: Updates  
 *		Changed "Watts" to "W", removed many newlines
 * 	10/20/15: Inception
 *		Added negative/positive reporting in configuration.
 *		Added Calibration Parameter for negating reversed clamps.  
 *		Commented and started color changing for Android users.
 *		Removed a lot of newlines
 */

metadata {
	definition (
		name: 		"Aeon Labs Home Energy Meter",
		namespace: 	"bridaus",
		category: 	"Green Living",
		author: 	"Brian Gudauskas"
	) 

	{
	capability "Energy Meter"
	capability "Power Meter"
	capability "Configuration"
	capability "Sensor"
	capability "Refresh"
	capability "Polling"
	capability "Battery"

	attribute "energy", "number"
	attribute "power", "number"
	attribute "volts", "number"
	attribute "voltage", "number"		// We'll deliver both, since the correct one is not defined anywhere (TODO fix?)
	attribute "amps", "number"

	attribute "energyDisp", "number"
	attribute "energyOne", "number"
	attribute "energyTwo", "number"

	attribute "powerDisp", "number"
	attribute "powerOne", "number"
	attribute "powerTwo", "number"

	attribute "voltsDisp", "number"
	attribute "voltsOne", "number"
	attribute "voltsTwo", "number"

	attribute "ampsDisp", "number"
	attribute "ampsOne", "number"
	attribute "ampsTwo", "number"        

	command "reset"
	command "configure"
	command "refresh"
	command "poll"
	command "toggleDisplay"

	// v1		fingerprint deviceId: "0x2101", inClusters: " 0x70,0x31,0x72,0x86,0x32,0x80,0x85,0x60"
	fingerprint deviceId: "0x3101", inClusters: "0x70,0x32,0x60,0x85,0x56,0x72,0x86"
	}

	// tile definitions
	tiles {
	
	// Watts row
		valueTile("powerDisp", "device.powerDisp") {
			state (
				"default",
				label:'${currentValue} W',
				icon: "st.Lighting.light14",
				foregroundColor: "#000000",
				backgroundColors:[
					[value: -9000, color: "#00cc00"],
					[value: -6000, color: "#23d000"],
					[value: -3000, color: "#47d400"],
					[value: -1000, color: "#6cd900"],
					[value: 0, color: "#6cd900"],
					[value: 1000, color: "#e6e600"],
					[value: 3000, color: "#eac300"],
					[value: 6000, color: "#ee9f00"],
					[value: 9000, color: "#f27900"],
					[value: 12000, color: "#f75200"],
					[value: 15000, color: "#fb2a00"],
					[value: 18000, color: "#ff0000"]
				]
			)
		}
		valueTile("powerOne", "device.powerOne", decoration: "flat") {
			state(
				"default",
				label:'${currentValue}',
				foregroundColor: "#000000",
			)
		}
		valueTile("powerTwo", "device.powerTwo", decoration: "flat") {
			state(
				"default",
				label:'${currentValue}',
				foregroundColor: "#000000",
			)
		}

	// Power row
		valueTile("energyDisp", "device.energyDisp", decoration: "flat") {
			state(
				"default",
				label: '${currentValue} kWh',
				foregroundColor: "#000000"
			)
		}
		valueTile("energyOne", "device.energyOne", decoration: "flat") {
			state(
				"default",
				label: '${currentValue}',
				foregroundColor: "#000000"
			)
		}
		valueTile("energyTwo", "device.energyTwo", decoration: "flat") {
			state(
				"default",
				label: '${currentValue}',
				foregroundColor: "#000000"
			)
		}

	// Volts row
		valueTile("voltsDisp", "device.voltsDisp") {
			state(
				"default",
				label: '${currentValue} V',
				backgroundColors:[
					[value: 114.0, color: "#ff0000"],
					[value: 120.0, color: "#00cc00"],
					[value: 126.0, color: "#ff0000"]
				]
			)
		}
		valueTile("voltsOne", "device.voltsOne", decoration: "flat") {
			state(
				"default",
				label:'${currentValue}',
			)
		}
		valueTile("voltsTwo", "device.voltsTwo", decoration: "flat") {
			state(
				"default",
				label:'${currentValue}',
			)
		}

	// Amps row
		valueTile("ampsDisp", "device.ampsDisp") {
			state (
				"default",
				label: '${currentValue} A',
				foregroundColor: "#000000",
				color: "#000000",
				backgroundColors:[
					[value: 0, color: "#6cd900"],
					[value: 25, color: "#e6e600"],
					[value: 50, color: "#eac300"],
					[value: 75, color: "#ee9f00"],
					[value: 100, color: "#f27900"],
					[value: 125, color: "#f75200"],
					[value: 150, color: "#fb2a00"],
					[value: 175, color: "#ff0000"]
				]
			)
		}
		valueTile("ampsOne", "device.ampsOne", decoration: "flat") {
			state(
				"default",
				label:'${currentValue}',
				foregroundColor: "#000000",
			)
		}
		valueTile("ampsTwo", "device.ampsTwo", decoration: "flat") {
			state(
				"default",
				label:'${currentValue}',
				foregroundColor: "#000000",
			)
		}

	// Controls row
		standardTile("reset", "command.reset", inactiveLabel: false) {
			state "default", label:'reset', action:"reset", icon: "st.Health & Wellness.health7"
		}
		standardTile("refresh", "command.refresh", inactiveLabel: false) {
			state "default", label:'refresh', action:"refresh.refresh", icon:"st.secondary.refresh-icon"
		}
		standardTile("configure", "command.configure", inactiveLabel: false) {
			state "configure", label:'', action: "configure", icon:"st.secondary.configure"
		}
		standardTile("toggle", "command.toggleDisplay", inactiveLabel: false) {
			state "default", label: "toggle", action: "toggleDisplay", icon: "st.motion.motion.inactive"
		}
		/* HEMv1 has a battery; v2 is line-powered */
		 valueTile("battery", "device.battery", decoration: "flat") {
			state "battery", label:'${currentValue}% battery', unit:""
		}

// HEM Version Configuration only needs to be done here - comments to choose what gets displayed

		main (["powerDisp","energyDisp","energyTwo",
			"ampsDisp","voltsDisp",				// Comment out this one for HEMv1
			])
		details([
			"energyOne","energyDisp","energyTwo",
			"powerOne","powerDisp","powerTwo",
			"ampsOne","ampsDisp","ampsTwo",			// Comment out these two lines for HEMv1
			"voltsOne","voltsDisp","voltsTwo",		// Comment out these two lines for HEMv1
			"reset","refresh","toggle",
		//	"battery",					// Include this for HEMv1	
			"configure"
		])
	}
    preferences {
    	input "kWhCost", "number", title: "\$/kWh (0.17)", description: "0.17", defaultValue: 0.17, displayDuringSetup: true
    	input "calibrationValue", "number", title: "Calibration for Power Reporting (Set to -1 to invert clamps)", defaultValue: 1, displayDuringSetup: true
    	input "wattsDiff", "number", title: "Threshold to Report Watts (0-60000)", defaultValue: 20, displayDuringSetup: true
    }
}

def installed() {
	state.display = 1
	reset()						// The order here is important
	configure()					// Since reports can start coming in even before we finish configure()
	refresh()
}

def updated() {
	configure()
	resetDisplay()
	refresh()
}

def parse(String description) {
//	log.debug "Parse received ${description}"
	def result = null
	def cmd = zwave.parse(description, [0x31: 1, 0x32: 1, 0x60: 3])
	if (cmd) {
		result = createEvent(zwaveEvent(cmd))
	}
	if (result) { 
		log.debug "Parse returned ${result?.descriptionText}"
		return result
	} else {
	}
}

def zwaveEvent(physicalgraph.zwave.commands.meterv1.MeterReport cmd) {
    def dispValue
    def newValue
    def formattedValue
    def MAX_AMPS = 220
    def MAX_WATTS = 24000
    
	def timeString = new Date().format("h:mm a", location.timeZone)
    
    if (cmd.meterType == 33) {
		if (cmd.scale == 0) {
        	newValue = (Math.round(cmd.scaledMeterValue * 100)/100) * calibrationValue
        	if (newValue != state.energyValue) {
            	log.debug "Energy Since Last Reset: ${state.energyDispSinceReset}"
                state.energyValue = newValue
                
		newValue = newValue - state.energyDispSinceReset  //calculated since last reset
		formattedValue = String.format("%5.2f", newValue)
                sendEvent(name: "energyDisp", value: formattedValue, unit: "kWh", displayed: false)
                BigDecimal costDecimal = newValue * ( kWhCost ?: 0.17 as BigDecimal )
                def costDisplay = String.format("%5.2f",costDecimal)
                state.costDisp = "Cost\n\$"+costDisplay
                if (state.display == 1) { sendEvent(name: "energyTwo", value: state.costDisp, unit: "", displayed: false) }
                [name: "energy", value: newValue, unit: "kWh"]
            }
		} 
		else if (cmd.scale == 1) {
            newValue = Math.round( cmd.scaledMeterValue * 100) * calibrationValue / 100
            if (newValue != state.energyValue) {
            	formattedValue = String.format("%5.2f", newValue)
    		dispValue = "${formattedValue} kVAh"
                sendEvent(name: "energyDisp", value: dispValue, unit: "kVAh", displayed: false)
                state.energyValue = newValue
		[name: "energy", value: newValue, unit: "kVAh"]
            }
		}
		else if (cmd.scale==2) {				
        	newValue = Math.round(cmd.scaledMeterValue) * calibrationValue		// really not worth the hassle to show decimals for Watts
            if (newValue > MAX_WATTS) { return }				// Ignore values beyond what HEM can report
        	if (newValue != state.powerValue) {
                sendEvent(name: "powerDisp", value: newValue as Number, unit: "W", displayed: false)
                
                if (newValue < state.powerLow) {
                	dispValue = newValue+" W\n"+timeString
                	if (state.display == 1) { sendEvent(name: "powerOne", value: dispValue, unit: "W")	}
                    state.powerLow = newValue
                    state.powerLowDisp = dispValue
                }
                if (newValue > state.powerHigh) {
                	dispValue = newValue+" W\n"+timeString
                	if (state.display == 1) { sendEvent(name: "powerTwo", value: dispValue, unit: "W")	}
                    state.powerHigh = newValue
                    state.powerHighDisp = dispValue
                }
                state.powerValue = newValue
                [name: "power", value: newValue, unit: "W"]
            }
		}
 	}
    else if (cmd.meterType == 161) {
    	if (cmd.scale == 0) {
        	newValue = Math.round( cmd.scaledMeterValue * 100) / 100
        	if (newValue != state.voltsValue) {
        		formattedValue = String.format("%5.2f", newValue)
                	sendEvent(name: "voltsDisp", value: newValue, unit: "V",  displayed: false)

                if (newValue < state.voltsLow) {
                	dispValue = formattedValue+" V\n"+timeString                	
                	if (state.display == 1) { sendEvent(name: "voltsOne", value: dispValue, unit: "V")	}
                    state.voltsLow = newValue
                    state.voltsLowDisp = dispValue
                }
                if (newValue > state.voltsHigh) {
                    dispValue = formattedValue+" V\n"+timeString
                	if (state.display == 1) { sendEvent(name: "voltsTwo", value: dispValue, unit: "V") }
                    state.voltsHigh = newValue
                    state.voltsHighDisp = dispValue
                }                
                state.voltsValue = newValue
                sendEvent( name: "voltage", value: newValue, unit: "V")
				[name: "volts", value: newValue, unit: "V"]
            }
        }
        else if (cmd.scale==1) {
        	newValue = Math.round( cmd.scaledMeterValue * 100) / 100
            if ( newValue > MAX_AMPS) { return }			
        	if (newValue != state.ampsValue) {
        		formattedValue = String.format("%5.2f", newValue)
                sendEvent(name: "ampsDisp", value: newValue, unit: "A", displayed: false)
                
                if (newValue < state.ampsLow) {
                	dispValue = formattedValue+" A\n"+timeString
                	if (state.display == 1) { sendEvent(name: "ampsOne", value: dispValue, unit: "A") }
                    state.ampsLow = newValue
                    state.ampsLowDisp = dispValue
                }
                if (newValue > state.ampsHigh) {
                	dispValue = formattedValue+" A\n"+timeString
                	if (state.display == 1) { sendEvent(name: "ampsTwo", value: dispValue, unit: "A") }
                    state.ampsHigh = newValue
                    state.ampsHighDisp = dispValue
                }                
                state.ampsValue = newValue
				[name: "amps", value: newValue, unit: "A"]
            }
        }
    }           
}

def zwaveEvent(physicalgraph.zwave.commands.multichannelv3.MultiChannelCmdEncap cmd) {
	def dispValue
	def newValue
	def formattedValue
	def MAX_AMPS = 220
	def MAX_WATTS = 24000

   	if (cmd.commandClass == 50) {    
   		def encapsulatedCommand = cmd.encapsulatedCommand([0x30: 1, 0x31: 1]) // can specify command class versions here like in zwave.parse
		if (encapsulatedCommand) {
			if (cmd.sourceEndPoint == 1) {
				if (encapsulatedCommand.scale == 2 ) { 										//Watts
//                	log.trace "L1 Watts Raw: ${encapsulatedCommand.scaledMeterValue}"
			newValue = Math.round(encapsulatedCommand.scaledMeterValue) * calibrationValue
                    if (newValue > MAX_WATTS) { return }
					formattedValue = newValue as String
					dispValue = "${formattedValue} W"
					if (dispValue != state.powerL1Disp) {
						state.powerL1Disp = dispValue
						if (state.display == 2) {
							[name: "powerOne", value: dispValue, unit: "W"]
						}
						else {
						}
					}
				} 
				else if (encapsulatedCommand.scale == 0 ){ 										//kWh
					newValue = Math.round(encapsulatedCommand.scaledMeterValue * 100) * calibrationValue / 100
					formattedValue = String.format("%5.2f", newValue)
					dispValue = "${formattedValue} kWh"
					if (dispValue != state.energyL1Disp) {
						state.energyL1Disp = dispValue
						if (state.display == 2) {
							[name: "energyOne", value: dispValue, unit: "kWh"]
						}
						else {
						}
					}
				}
				else if (encapsulatedCommand.scale == 1 ){									//kVah
					newValue = Math.round(encapsulatedCommand.scaledMeterValue * 100) * calibrationValue / 100
					formattedValue = String.format("%5.2f", newValue)
					dispValue = "${formattedValue} kVAh"
					if (dispValue != state.energyL1Disp) {
						state.energyL1Disp = dispValue
						if (state.display == 2) {
							[name: "energyOne", value: dispValue, unit: "kVAh"]
						}
						else {
						}
					}
				}
				else if (encapsulatedCommand.scale == 5 ) {									//Amps
					newValue = Math.round(encapsulatedCommand.scaledMeterValue * 100) / 100
                    if (newValue > MAX_AMPS) { return }
					formattedValue = String.format("%5.2f", newValue)
					dispValue = "${formattedValue} A"
					if (dispValue != state.ampsL1Disp) {
						state.ampsL1Disp = dispValue
						if (state.display == 2) {
							[name: "ampsOne", value: dispValue, unit: "A"]
						}
						else {
						}
					}
               	} 
/* Ignore voltage updates, because they always match the current Total Voltage               	
               	else if (encapsulatedCommand.scale == 4 ){
               		newValue = Math.round(encapsulatedCommand.scaledMeterValue * 100) / 100
					formattedValue = String.format("%5.2f", newValue)
					dispValue = "${formattedValue} V"
					if (dispValue != statevoltsL1Disp) {
						state.voltsL1Disp = dispValue
						if (state.display == 2) {
							[name: "voltsOne", value:dispValue, unit: "V"]
						}
						else {
						}
					}
               	}               
*/               	
			} 
			else if (cmd.sourceEndPoint == 2) {
				if (encapsulatedCommand.scale == 2 ){									//Watts
//                	log.trace "L2 Watts Raw: ${encapsulatedCommand.scaledMeterValue}"
					newValue = Math.round(encapsulatedCommand.scaledMeterValue) * calibrationValue
                    if (newValue > MAX_S ) { return }
					formattedValue = newValue as String
					dispValue = "${formattedValue} W"
					if (dispValue != state.powerL2Disp) {
						state.powerL2Disp = dispValue
						if (state.display == 2) {
							[name: "powerTwo", value: dispValue, unit: "W"]
						}
						else {
						}
					}
				} 
				else if (encapsulatedCommand.scale == 0 ){								//kWh
					newValue = Math.round(encapsulatedCommand.scaledMeterValue * 100) / 100
					formattedValue = String.format("%5.2f", newValue)
					dispValue = "${formattedValue} kWh"
					if (dispValue != state.energyL2Disp) {
						state.energyL2Disp = dispValue
						if (state.display == 2) {
							[name: "energyTwo", value: dispValue, unit: "kWh"]
						}
						else {
						}
					}
				} 
				else if (encapsulatedCommand.scale == 1 ){									//kVAh
					newValue = Math.round(encapsulatedCommand.scaledMeterValue * 100) / 100
					formattedValue = String.format("%5.2f", newValue)
					dispValue = "${formattedValue} kVAh"
					if (dispValue != state.energyL2Disp) {
						state.energyL2Disp = dispValue
						if (state.display == 2) {
							[name: "energyTwo", value: dispValue, unit: "kVAh"]
						}
						else {
						}
					}
				}				
				else if (encapsulatedCommand.scale == 5 ){										//Amps
               		newValue = Math.round(encapsulatedCommand.scaledMeterValue * 100) / 100
                    if (newValue > MAX_AMPS) { return } 
					formattedValue = String.format("%5.2f", newValue)
					dispValue = "${formattedValue} A"
					if (dispValue != state.ampsL2Disp) {
						state.ampsL2Disp = dispValue
						if (state.display == 2) {
							[name: "ampsTwo", value: dispValue, unit: "A"]
						}
						else {
						}
					}
				}
/* Ignore voltage updates, because they always match the current Total Voltage
	    		else if (encapsulatedCommand.scale == 4 ){
               		newValue = Math.round(encapsulatedCommand.scaledMeterValue * 100) / 100
					formattedValue = String.format("%5.2f", newValue)
					dispValue = "${formattedValue} V"
					if (dispValue != statevoltsL2Disp) {
						state.voltsL2Disp = dispValue
						if (state.display == 2) {
							[name: "voltsTwo", value:dispValue, unit: "V"]
						}
						else {
						}
					}
               	}               			
*/               	
			}
		}
	}
}

def zwaveEvent(physicalgraph.zwave.commands.batteryv1.BatteryReport cmd) {
	def map = [:]
	map.name = "battery"
	map.unit = "%"
	
	if (cmd.batteryLevel == 0xFF) {
		map.value = 1
		map.descriptionText = "${device.displayName} battery is low"
		map.isStateChange = true
	} 
	else {
		map.value = cmd.batteryLevel
	}
	log.debug map
	return map
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
	// Handles all Z-Wave commands we aren't interested in
    log.debug "Unhandled event ${cmd}"
	[:]
}

def refresh() {			// Request HEMv2 to send us the latest values for the 4 we are tracking
	log.debug "refresh()"
    
	delayBetween([
		zwave.meterV2.meterGet(scale: 0).format(),		// Change 0 to 1 if international version
		zwave.meterV2.meterGet(scale: 2).format(),
		zwave.meterV2.meterGet(scale: 4).format(),
		zwave.meterV2.meterGet(scale: 5).format()
	])
    resetDisplay()
}

def poll() {
	log.debug "poll()"
	refresh()
}

def toggleDisplay() {
	log.debug "toggleDisplay()"
    
	if (state.display == 1) { 
		state.display = 2 
	}
	else { 
		state.display = 1
	}
	resetDisplay()
}

def resetDisplay() {
	log.debug "resetDisplay() - energyL1Disp: ${state.energyL1Disp}"
	
	if ( state.display == 1 ) {
    	sendEvent(name: "voltsOne", value: state.voltsLowDisp, unit: "V")
    	sendEvent(name: "voltsTwo", value: state.voltsHighDisp, unit: "V")

		sendEvent(name: "ampsOne", value: state.ampsLowDisp, unit: "A")    
    	sendEvent(name: "ampsTwo", value: state.ampsHighDisp, unit: "A")

		sendEvent(name: "powerOne", value: state.powerLowDisp, unit: "W")     
    	sendEvent(name: "powerTwo", value: state.powerHighDisp, unit: "W")

		sendEvent(name: "energyOne", value: state.lastResetTime, unit: "")
    	sendEvent(name: "energyTwo", value: state.costDisp, unit: "")
	}
	else {
    	sendEvent(name: "voltsOne", value: "L1", unit: "V")
		sendEvent(name: "voltsTwo", value: "L2", unit: "V")
        
    	sendEvent(name: "ampsOne", value: state.ampsL1Disp, unit: "A")    
    	sendEvent(name: "ampsTwo", value: state.ampsL2Disp, unit: "A")
        
		sendEvent(name: "powerOne", value: state.powerL1Disp, unit: "W")     
    	sendEvent(name: "powerTwo", value: state.powerL2Disp, unit: "W")
        
    	sendEvent(name: "energyOne", value: state.lastResetTime, unit: "")	
    	sendEvent(name: "energyTwo", value: state.energyL2Disp, unit: "")
	}
}

def reset() {
	log.debug "reset()"

	if (!state.energyValue) { state.energyValue = 0.0 }	// fix if null from new install

	state.energyDispSinceReset = state.energyValue  //store reset energy

	state.energyValue = 0.0
	state.powerValue = 0.0
	state.ampsValue = 0.0
	state.voltsValue = 0.0
	
	state.powerHigh = -99999
	state.powerHighDisp = "--"
	state.powerLow = 99999
	state.powerLowDisp = "--"
	state.ampsHigh = 0
	state.ampsHighDisp = "--"
	state.ampsLow = 999
	state.ampsLowDisp = "--"
	state.voltsHigh = 0
	state.voltsHighDisp = "--"
	state.voltsLow = 999
	state.voltsLowDisp = ""

	state.energyL1Disp = "--"
	state.energyL2Disp = "--"
	state.powerL1Disp = "--"
	state.powerL2Disp = "--"
	state.ampsL1Disp = "--"
	state.ampsL2Disp = "--"
	state.voltsL1Disp = "--"
	state.voltsL2Disp = "--"

	if (!state.display) { state.display = 1 }	// Sometimes it appears that installed() isn't called

	def dateString = new Date().format("M/d/YY", location.timeZone)
	def timeString = new Date().format("h:mm a", location.timeZone)    
	state.lastResetTime = "Since\n"+dateString+"\n"+timeString
	state.costDisp = "Cost\n--"
	
	resetDisplay()

	sendEvent(name: "energyDisp", value: "", unit: "kWh")
	sendEvent(name: "powerDisp", value: "", unit: "W")	
	sendEvent(name: "ampsDisp", value: "", unit: "A")
	sendEvent(name: "voltsDisp", value: "", unit: "V")

// No V1 available
	def cmd = delayBetween( [
		zwave.meterV2.meterReset().format(),			// Reset all values
		zwave.meterV2.meterGet(scale: 0).format(),		// Request the values we are interested in (0-->1 for kVAh)
		zwave.meterV2.meterGet(scale: 2).format(),
		zwave.meterV2.meterGet(scale: 4).format(),
		zwave.meterV2.meterGet(scale: 5).format()
	], 2000)
    cmd
    
    configure()
}

def configure() {
	log.debug "configure()"
    
	def cmd = delayBetween([
		zwave.configurationV1.configurationSet(parameterNumber: 2, size: 1, scaledConfigurationValue: 1).format(),				// Enable (=1) pos/neg reporting
		zwave.configurationV1.configurationSet(parameterNumber: 3, size: 1, scaledConfigurationValue: 1).format(),				// Disable (=0) selective reporting
		zwave.configurationV1.configurationSet(parameterNumber: 4, size: 2, scaledConfigurationValue: wattsDiff ?: 0).format(),	// Threshold for watts reporting

		zwave.configurationV1.configurationSet(parameterNumber: 101, size: 4, scaledConfigurationValue: 6149).format(),   		// All L1/L2 kWh, total Volts & kWh
		zwave.configurationV1.configurationSet(parameterNumber: 111, size: 4, scaledConfigurationValue: 60).format(), 			// Every 60 seconds

		zwave.configurationV1.configurationSet(parameterNumber: 102, size: 4, scaledConfigurationValue: 1572872).format(),		// Amps L1, L2, Total
		zwave.configurationV1.configurationSet(parameterNumber: 112, size: 4, scaledConfigurationValue: 30).format(), 			// every 30 seconds

		zwave.configurationV1.configurationSet(parameterNumber: 103, size: 4, scaledConfigurationValue: 770).format(),			// Power (Watts) L1, L2, Total
		zwave.configurationV1.configurationSet(parameterNumber: 113, size: 4, scaledConfigurationValue: 6).format() 			// every 6 seconds
	], 2000)
	log.debug cmd

	cmd
}