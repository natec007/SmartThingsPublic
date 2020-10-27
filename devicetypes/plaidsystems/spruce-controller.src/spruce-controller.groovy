/**
 *  Copyright 2020 PlaidSystems
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
 Version v3.1
 * Change to work with standard ST automation options
 * use standard switch since custom attributes still don't work in automations
 * Add schedule minute times to settings
 * Add split cycle to settings
 * deprecate Spruce Scheduler compatibility
 
 Version v3.0
 * Update for new Samsung SmartThings app
 * update vid with status, message, rainsensor
 * maintain compatibility with Spruce Scheduler
 * Requires Spruce Valve as child device 
 
 Version v2.7
 * added Rain Sensor = Water Sensor Capability
 * added Pump/Master
 * add "Dimmer" to Spruce zone child for manual duration
 
**/
 
private def getVERSION() { return "v3.1 10-2020" }
 
metadata {
	definition (name: "Spruce Controller", namespace: "plaidsystems", author: "Plaid Systems", mnmn: "SmartThingsCommunity", vid: "566e4d4f-93bc-3c86-b7e1-8a4e212683b0"){//vid: "5e1426e6-1943-3331-8880-6c3e7743f2cc"){
		capability "Actuator"
        capability "Switch"
        capability "Sensor"
        capability "Health Check"
        capability "heartreturn55003.status"
        capability "heartreturn55003.controllerState"
        capability "heartreturn55003.rainSensor"
        
        capability "Configuration"
        capability "Refresh"        
        
        attribute "status", "string"
        attribute "controllerState", "string"
        attribute "rainSensor", "string"
        
        command "on"
        command "off"
        command "setStatus"
        command "setRainSensor"
        command "setControllerState"
                
		//new release
        fingerprint manufacturer: "PLAID SYSTEMS", model: "PS-SPRZ16-01", deviceJoinName: "Spruce Irrigation Controller (Gen1)"
        
	}

	preferences {
        input title: "Version", description: getVERSION(), displayDuringSetup: true, type: "paragraph", element: "paragraph"
                        
        input title: "Device settings", displayDuringSetup: true, type: "paragraph", element: "paragraph",
        	description: "Settings for automatic operations and device touch buttons."
        
        input "touchButtonDuration", "integer", title: "Automatic turn off time when touch buttons are used on device? (minutes)", required: false, displayDuringSetup: true
        input "rainSensorEnable", "bool", title: "Rain Sensor Attached?", required: false, displayDuringSetup: true
                
        input name: "pumpMasterZone", type: "enum", title: "Pump or Master zone", description: "This zone will turn on and off anytime another zone is turned on or off", required: false,
        	options: ["Zone 1", "Zone 2", "Zone 3", "Zone 4", "Zone 5", "Zone 6", "Zone 7", "Zone 8", "Zone 9", "Zone 10", "Zone 11", "Zone 12", "Zone 13", "Zone 14", "Zone 15", "Zone 16"]
        input "splitCycle", "bool", title: "Cycle scheduled watering time to reduce runoff?", required: false, displayDuringSetup: true
        input "valveDelay", "integer", title: "Delay between valves when a schedule runs? (seconds)", required: false, displayDuringSetup: true
        
        input title: "Zone devices", displayDuringSetup: true, type: "paragraph", element: "paragraph",
        	description: "Enable Zones for manual control and automations. Set schedule minutes to water a zone anytime controller state is switched on."
        
        input name: "z1", type: "bool", title: "Zone 1", displayDuringSetup: true
        input name: "z1Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z2", type: "bool", title: "Zone 2", displayDuringSetup: true
        input name: "z2Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z3", type: "bool", title: "Zone 3", displayDuringSetup: true
        input name: "z3Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z4", type: "bool", title: "Zone 4", displayDuringSetup: true
        input name: "z4Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z5", type: "bool", title: "Zone 5", displayDuringSetup: true
        input name: "z5Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z6", type: "bool", title: "Zone 6", displayDuringSetup: true
        input name: "z6Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z7", type: "bool", title: "Zone 7", displayDuringSetup: true
        input name: "z7Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z8", type: "bool", title: "Zone 8", displayDuringSetup: true
        input name: "z8Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z9", type: "bool", title: "Zone 9", displayDuringSetup: true
        input name: "z9Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z10", type: "bool", title: "Zone 10", displayDuringSetup: true
        input name: "z10Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z11", type: "bool", title: "Zone 11", displayDuringSetup: true
        input name: "z11Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z12", type: "bool", title: "Zone 12", displayDuringSetup: true
        input name: "z12Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z13", type: "bool", title: "Zone 13", displayDuringSetup: true
        input name: "z13Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z14", type: "bool", title: "Zone 14", displayDuringSetup: true
        input name: "z14Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z15", type: "bool", title: "Zone 15", displayDuringSetup: true
        input name: "z15Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
        input name: "z16", type: "bool", title: "Zone 16", displayDuringSetup: true
        input name: "z16Duration", type: "integer", title: "schedule minutes", displayDuringSetup: true
    }	
}

//----------------------zigbee parse-------------------------------//

// Parse incoming device messages to generate events
def parse(String description) {	
	//log.debug "Parse description ${description}"
    def result = []
    def endpoint, value, command
    def map = zigbee.parseDescriptionAsMap(description)
    if (!map.raw) log.debug "map ${map}"
    
    if (description.contains("on/off")){    	
        command = 1
        value = description[-1]
    }
    else {
    	endpoint = ( map.sourceEndpoint == null ? hextoint(map.endpoint) : hextoint(map.sourceEndpoint) )
    	value = ( map.sourceEndpoint == null ? hextoint(map.value) : null )    
    	command = (value != null ? commandType(endpoint, map.clusterInt) : null)
    }
    
    if (command != null) log.debug "${command} >> endpoint ${endpoint} value ${value} cluster ${map.clusterInt}"
    switch(command) {
      case "alarm":        
        result.push(createEvent(name: "status", value: "alarm"))
        break
      case "program":
        def scheduleValue = (value == 1 ? "on" : "off")
        if (scheduleValue == "off") result.push(createEvent(name: "status", value: "Schedule ${scheduleValue}"))
        result.push(createEvent(name: "switch", value: scheduleValue, displayed: false))
        result.push(createEvent(name: "controllerState", value: scheduleValue))
        break
      case "zone":
      	def onoff = (value == 1 ? "open" : "closed")
        def child = childDevices.find{it.deviceNetworkId == "${device.deviceNetworkId}:${endpoint}"}
        if (child) child.sendEvent(name: "valve", value: onoff)
        
        if (device.latestValue("controllerStatus") == "off") return setTouchButtonDuration()
        break
      case "rainsensor":
        def rainSensor = (value == 1 ? "wet" : "dry")
        if (!settings.rainSensorEnable) rainSensor = "disabled"
        result.push(createEvent(name: "rainSensor", value: rainSensor))
        break
      case "refresh":
        //log.debug "refresh"
        
        break
      default:
      	//log.debug "null"
        break
    }
    
	//log.debug "result: ${result}"
	return result
}

def commandType(endpoint, cluster){
	if (cluster == 9) return "alarm"
	else if (endpoint == 1) return "program"
    else if (endpoint in 2..17) return "zone"
    else if (endpoint == 18) return "rainsensor"
    else if (endpoint == 19) return "refresh"
}

//--------------------end zigbee parse-------------------------------//

def installed() {	
	if (!childDevices) {    	
    	removeChildDevices()
		createChildDevices()
        response(refresh() + configure())
	}
    initialize()
}

def updated() {
	log.debug "updated"
    
    createChildDevices()
    initialize()
}

def initialize(){
    sendEvent(name: "switch", value: "off", displayed: false)
    sendEvent(name: "controllerState", value: "off", displayed: false)
    sendEvent(name: "status", value: "Initialize")
    //sendEvent(name: "rainSensor", value: "disabled")
    response(setDeviceSettings() + setTouchButtonDuration() + setRainSensor())
}


private void createChildDevices() {	
    log.debug "create children"
    def pumpMasterZone = (settings.pumpMasterZone != null ? settings.pumpMasterZone.replaceFirst("Zone ","").toInteger() : null)
    
    //create, rename, or remove child
    for (i in 1..16){
    	//endpoint is zone number +1
        def endpoint = i + 1
        
        if(settings."${"z${i}"}"){
        	def child = childDevices.find{it.deviceNetworkId == "${device.deviceNetworkId}:${endpoint}"}
            //create child
            if (!child){
            	def childLabel = (state.oldLabel != null ? "${state.oldLabel} Zone${i}" : "Spruce Zone${i}")
                if (pumpMasterZone == i) childLabel = "Spruce PM Zone${i}"
                child = addChildDevice("Spruce Valve", "${device.deviceNetworkId}:${endpoint}", device.hubId,
                        [completedSetup: true, label: "${childLabel}",
                         isComponent: false])
                         log.debug "${child}"
                    child.sendEvent(name: "switch", value: "off", displayed: false)
            }
            //or rename child
            else if (device.label != state.oldLabel){
            	def childLabel = (state.oldLabel != null ? "${state.oldLabel} Zone${i}" : "Spruce Zone${i}")
                if (pumpMasterZone == i) childLabel = "Spruce PM Zone${i}"
            	child.setLabel("${childLabel}")
            }
        }
        //remove child
        else if (childDevices.find{it.deviceNetworkId == "${device.deviceNetworkId}:${endpoint}"}){
        	deleteChildDevice("${device.deviceNetworkId}:${endpoint}")
        }
        
    }
    
    state.oldLabel = device.label
}

private removeChildDevices() {
	log.debug "remove all children"
	
    //get and delete children avoids duplicate children
    def children = getChildDevices()    
    if(children != null){
        children.each{
            deleteChildDevice(it.deviceNetworkId)
        }
    }       
}


//----------------------------------commands--------------------------------------//

def setStatus(status){
	log.debug "status ${status}"
    sendEvent(name: "status", value: status, descriptionText: "Initialized")
}

def setRainSensor() {
    log.debug "Rain sensor: ${settings.rainSensorEnable}"

    if (settings.rainSensorEnable) return zigbee.writeAttribute(0x0F, 0x51, 0x10, 1, ["destEndpoint": 18])
    else return zigbee.writeAttribute(0x0F, 0x51, 0x10, 0, ["destEndpoint": 18])
}

//cahnge the device settings for automatically starting a pump or master zone, set the controller to split scheduled watering cycles, set a delay between scheduled valves
def setDeviceSettings() {	
    def pumpMasterZone = (settings.pumpMasterZone != null ? settings.pumpMasterZone.replaceFirst("Zone ","").toInteger() : null)
    def splitCycle = (settings.splitCycle == true ? 2 : 1)
    def valveDelay = (settings.valveDelay != null ? settings.valveDelay.toInteger() : 0)
    log.debug "Pump/Master: ${pumpMasterEndpoint} splitCycle: ${splitCycle} valveDelay: ${valveDelay}"
    
    def endpointMap = [:]
    int zone = 0
    while(zone <= 17)
    {
        //setup zone, 1=single cycle, 2=split cycle, 4=pump/master
        def zoneSetup = splitCycle
        if (zone == pumpMasterZone) zoneSetup = 4
        else if (zone == 0) zoneSetup = valveDelay
        
        def endpoint = zone + 1        
        endpointMap."${endpoint}" = "${zoneSetup}"
        zone++
    }
    
    return settingsMap(endpointMap, 4001)
}

//change the default time a zone will turn on when the buttons on the face of the controller are used
def setTouchButtonDuration(){
    def touchButtonDuration = (settings.touchButtonDuration != null ? settings.touchButtonDuration.toInteger() : 10)    
    log.debug "touchButtonDuration ${touchButtonDuration} mins"
        
    def sendCmds = []
    sendCmds.push(zigbee.writeAttribute(6, 0x4002, 0x21, touchButtonDuration, ["destEndpoint": 1]))
    return sendCmds
}

//controllerState
def setControllerState(state){
	log.debug "state ${state}"
    sendEvent(name: "controllerState", value: state, descriptionText: "Initialized")
    
    switch(state) {
    	case "on":    		
            if (!rainDelay()) {
            	sendEvent(name: "switch", value: "on", displayed: false)
                sendEvent(name: "status", value: "initialize schedule", descriptionText: "initialize schedule")
                startSchedule()
            }
            break
        case "off":        
    		sendEvent(name: "switch", value: "off", displayed: false)
    		zoff()
            break
        case "pause":        
    		pause()
            break
        case "resume":        
    		resume()
            break        
    }
}

//on & off from switch
def on() {
    log.debug "switch on"
    setControllerState("on")    
}

def off() {
	log.debug "switch off"
    setControllerState("off")    
}

def pause() {
    log.debug "pause"    
    sendEvent(name: "switch", value: "off", displayed: false)
    sendEvent(name: "controllerStatus", value: "pause", descriptionText: "pause on")
    zoff()
}

def resume() {
    log.debug "resume"    
    sendEvent(name: "switch", value: "on", displayed: false)
    sendEvent(name: "controllerStatus", value: "resume", descriptionText: "resume on")
    zon()
}

//set raindelay
def rainDelay() {
	if (settings.rainSensorEnable && device.latestValue("rainSensor") == "wet") {
		sendEvent(name: "switch", value: "off", displayed: false)
        sendEvent(name: "controllerState", value: "off")
    	sendEvent(name: "status", value: "rainy")
        return true
    }
    return false
}

//set schedule
def noSchedule() {
	sendEvent(name: "switch", value: "off", displayed: false)
    sendEvent(name: "controllerState", value: "off")
    sendEvent(name: "status", value: "Set schedule in settings")
}

// Commands to device
//program on/off
def zon() {	
    zigbee.command(6, 1, "", [destEndpoint: 1])
}
def zoff() {
    zigbee.command(6, 0, "", [destEndpoint: 1])
}

// Commands to zones/valves
def valveOn(valueMap) {
	//log.debug valueMap
    def endpoint = valueMap.dni.replaceFirst("${device.deviceNetworkId}:","").toInteger()
    def duration = (valueMap.duration != null ? valueMap.duration.toInteger() : 0)
    
    sendEvent(name: "status", value: "${valueMap.label} on for ${duration}min(s)", descriptionText: "Zone ${valueMap.label} on for ${duration}min(s)")
    
    zoneOn(endpoint, duration)
}

def valveOff(valueMap) {
	def endpoint = valueMap.dni.replaceFirst("${device.deviceNetworkId}:","").toInteger()    
        
    sendEvent(name: "status", value: "${valueMap.label} turned off", descriptionText: "${valueMap.label} turned off")

	zoneOff(endpoint)
}

def zoneOn(endpoint, duration) {
	//send duration from slider
	return zoneDuration(duration) + zigbee.command(6, 1, "", ["destEndpoint": endpoint])
}

def zoneOff(endpoint) {
	//reset touchButtonDuration to setting value
    return setTouchButtonDuration() + zigbee.command(6, 0, "", ["destEndpoint": endpoint])
}

def zoneDuration(int duration){
    def hexDuration = hex(duration)
    
    def sendCmds = []
    sendCmds.push(zigbee.writeAttribute(6, 0x4002, 0x21, duration, ["destEndpoint": 1]))
    return sendCmds
}

//------------------end commands----------------------------------//

//get times from settings and send to controller
def startSchedule(){
	def startRun = false
    def runTime, totalTime=0
    def scheduleTimes = []
    def i = 1
    while(i <= 16){
    	def endpoint = i + 1  
    	if (settings."z${i}" && settings."z${i}Duration" != null){        	
            runTime = Integer.parseInt(settings."z${i}Duration")
            totalTime += runTime
        	startRun = true
			
        	scheduleTimes.push(zigbee.writeAttribute(6, 0x4002, 0x21, runTime, ["destEndpoint": endpoint]))//"st wattr 0x${device.deviceNetworkId} ${endpoint} 0x06 0x4002 0x21 {00${runTime}}")
            scheduleTimes.push("delay 600")
        }
        else {
        	scheduleTimes.push(zigbee.writeAttribute(6, 0x4002, 0x21, 0, ["destEndpoint": endpoint]))//"st wattr 0x${device.deviceNetworkId} ${endpoint} 0x06 0x4002 0x21 {0000}")
            scheduleTimes.push("delay 600")
        }
        i++        
    }
    if (!startRun) return noSchedule()
    //start after scheduleTimes are sent
    scheduleTimes.push("st cmd 0x${device.deviceNetworkId} 1 6 1 {}")
    sendEvent(name: "status", value: "Scheduled for ${totalTime}min(s)", descriptionText: "Start schedule ending in ${totalTime} mins")
    return scheduleTimes
}

//write switch time settings map
def settingsMap(WriteTimes, attrType){
	
    def i = 1
    def runTime
    def sendCmds = []
    while(i <= 17){
    	if (WriteTimes."${i}"){        	
        	runTime = hex(Integer.parseInt(WriteTimes."${i}"))
		
        	if (attrType == 4001) sendCmds.push("st wattr 0x${device.deviceNetworkId} ${i} 0x06 0x4001 0x21 {00${runTime}}")
        	else sendCmds.push("st wattr 0x${device.deviceNetworkId} ${i} 0x06 0x4002 0x21 {00${runTime}}")
            sendCmds.push("delay 600")
        }
        i++
    }
    return sendCmds
}

//send switch time
def writeType(wEP, cycle){	
    "st wattr 0x${device.deviceNetworkId} ${wEP} 0x06 0x4001 0x21 {00" + hex(cycle) + "}"
}

//send switch off time
def writeTime(wEP, runTime){    
    "st wattr 0x${device.deviceNetworkId} ${wEP} 0x06 0x4002 0x21 {00" + hex(runTime) + "}"
}

//set reporting and binding
def configure() {
	// Device-Watch allows 2 check-in misses from device, checks every 2 hours
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: 2* 60 * 60, displayed: false, data: [protocol: "zigbee", hubHardwareId: device.hub.hardwareID])
	    
    config()    
}

def config(){
	configureHealthCheck()
    
	String zigbeeId = swapEndianHex(device.hub.zigbeeId)
	log.debug "Configuring Reporting and Bindings ${device.deviceNetworkId} ${device.zigbeeId}"
    
    def configCmds = [	
        //program on/off        
        "zdo bind 0x${device.deviceNetworkId} 1 1 6 {${device.zigbeeId}} {}", "delay 1000",
        "zdo bind 0x${device.deviceNetworkId} 1 1 0x09 {${device.zigbeeId}} {}", "delay 1000",        
        "zdo bind 0x${device.deviceNetworkId} 1 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        //zones 1-8
        "zdo bind 0x${device.deviceNetworkId} 2 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        "zdo bind 0x${device.deviceNetworkId} 3 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
		"zdo bind 0x${device.deviceNetworkId} 4 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        "zdo bind 0x${device.deviceNetworkId} 5 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        "zdo bind 0x${device.deviceNetworkId} 6 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        "zdo bind 0x${device.deviceNetworkId} 7 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        "zdo bind 0x${device.deviceNetworkId} 8 1 0x0F {${device.zigbeeId}} {}", "delay 1000",        
        "zdo bind 0x${device.deviceNetworkId} 9 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        //zones 9-16
        "zdo bind 0x${device.deviceNetworkId} 10 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        "zdo bind 0x${device.deviceNetworkId} 11 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
		"zdo bind 0x${device.deviceNetworkId} 12 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        "zdo bind 0x${device.deviceNetworkId} 13 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        "zdo bind 0x${device.deviceNetworkId} 14 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        "zdo bind 0x${device.deviceNetworkId} 15 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        "zdo bind 0x${device.deviceNetworkId} 16 1 0x0F {${device.zigbeeId}} {}", "delay 1000",        
        "zdo bind 0x${device.deviceNetworkId} 17 1 0x0F {${device.zigbeeId}} {}", "delay 1000",
        //rain sensor
        "zdo bind 0x${device.deviceNetworkId} 18 1 0x0F {${device.zigbeeId}} {}",
        
        "zcl global send-me-a-report 6 0 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 1", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 1", "delay 500",
       
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 2", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 3", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 4", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 5", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 6", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 7", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 8", "delay 500",      
        
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 9", "delay 500",
       
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 10", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 11", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 12", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 13", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 14", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 15", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 16", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 17", "delay 500",
        
        "zcl global send-me-a-report 0x0F 0x55 0x10 1 0 {01}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 18", "delay 500",
        
        "zcl global send-me-a-report 0x09 0x00 0x21 1 0 {00}", "delay 500",
        "send 0x${device.deviceNetworkId} 1 1", "delay 500"
	]
    return configCmds + setRainSensor()
}

//PING is used by Device-Watch in attempt to reach the Device
def ping() {
	log.debug "device health ping"    
    return zigbee.onOffRefresh()
}

def refresh() {
	log.debug "refresh pressed"
    
    def refreshCmds = [
        
        "st rattr 0x${device.deviceNetworkId} 2 0x0F 0x55", "delay 500",
        "st rattr 0x${device.deviceNetworkId} 3 0x0F 0x55", "delay 500",
        "st rattr 0x${device.deviceNetworkId} 4 0x0F 0x55", "delay 500",
        "st rattr 0x${device.deviceNetworkId} 5 0x0F 0x55", "delay 500",
        "st rattr 0x${device.deviceNetworkId} 6 0x0F 0x55", "delay 500",        
        "st rattr 0x${device.deviceNetworkId} 7 0x0F 0x55", "delay 500",
        "st rattr 0x${device.deviceNetworkId} 8 0x0F 0x55", "delay 500",        
        "st rattr 0x${device.deviceNetworkId} 9 0x0F 0x55", "delay 500",
        
        "st rattr 0x${device.deviceNetworkId} 10 0x0F 0x55", "delay 500",
        "st rattr 0x${device.deviceNetworkId} 11 0x0F 0x55", "delay 500",
        "st rattr 0x${device.deviceNetworkId} 12 0x0F 0x55", "delay 500",
        "st rattr 0x${device.deviceNetworkId} 13 0x0F 0x55", "delay 500",
        "st rattr 0x${device.deviceNetworkId} 14 0x0F 0x55", "delay 500",        
        "st rattr 0x${device.deviceNetworkId} 15 0x0F 0x55", "delay 500",
        "st rattr 0x${device.deviceNetworkId} 16 0x0F 0x55", "delay 500",
        "st rattr 0x${device.deviceNetworkId} 17 0x0F 0x55", "delay 500",
        
        "st rattr 0x${device.deviceNetworkId} 18 0x0F 0x51","delay 500",
 	
    ]
    
    //will trigger off command if checked during schedule initialization
    if (device.latestValue("switch") != "programWait") refreshCmds + ["st rattr 0x${device.deviceNetworkId} 1 0x0F 0x55", "delay 500"]
    
    return refreshCmds
}

def healthPoll() {
	log.debug "healthPoll()"
	def cmds = refresh()
	cmds.each { sendHubCommand(new physicalgraph.device.HubAction(it)) }
}

def configureHealthCheck() {
	Integer hcIntervalMinutes = 12
	if (!state.hasConfiguredHealthCheck) {
		log.debug "Configuring Health Check, Reporting"
		unschedule("healthPoll")
		runEvery5Minutes("healthPoll")
		def healthEvent = [name: "checkInterval", value: hcIntervalMinutes * 60, displayed: false, data: [protocol: "zigbee", hubHardwareId: device.hub.hardwareID]]
		// Device-Watch allows 2 check-in misses from device
		sendEvent(healthEvent)
		childDevices.each {
			it.sendEvent(healthEvent)
		}
		state.hasConfiguredHealthCheck = true
	}
}

//parse hex string and make integer
private hextoint(String hex) {
	Long.parseLong(hex, 16).toInteger()
}

private hex(value) {
	new BigInteger(Math.round(value).toString()).toString(16)
}

private String swapEndianHex(String hex) {
    reverseArray(hex.decodeHex()).encodeHex()
}

private byte[] reverseArray(byte[] array) {
    int i = 0;
    int j = array.length - 1;
    byte tmp;
    while (j > i) {
        tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
        j--;
        i++;
    }
    return array
}